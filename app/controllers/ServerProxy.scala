package controllers

import akka.actor.ActorSystem
import com.google.inject.AbstractModule
import com.google.inject.assistedinject.{Assisted, FactoryModuleBuilder}
import io.apibuilder.validation.FormData
import java.net.URI
import javax.inject.Inject

import akka.stream.ActorMaterializer
import play.api.Logger
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}
import lib._

import scala.annotation.tailrec
import scala.concurrent.duration.{FiniteDuration, SECONDS}

/**
  * Server Proxy is responsible for proxying all requests to a given
  * server. The primary purpose of the proxy is to segment our thread
  * pools by server - so if one server is having difficulty, it is
  * less likely to impact other servers.
  */
trait ServerProxy {

  def server: Server

  def proxy(
    request: ProxyRequest,
    route: Route,
    token: ResolvedToken,
    organization: Option[String] = None,
    partner: Option[String] = None
  ): Future[play.api.mvc.Result]

}

object ServerProxy {

  val DefaultContextName = s"default-server-context"

  trait Factory {
    def apply(server: Server): ServerProxy
  }

  /**
    * Maps a query string that may contain multiple values per parameter
    * to a sequence of query parameters. Uses the underlying form data to
    * also upcast the parameters (mapping the incoming parameters to a json
    * document, upcasting, then back to query parameters)
    *
    * @todo Add example query string
    * @example
    * {{{
    *    query(
    *      Map[String, Seq[String]](
    *        "foo" -> Seq("a", "b"),
    *        "foo2" -> Seq("c")
    *      )
    *    ) == Seq(
    *      ("foo", "a"),
    *      ("foo", "b"),
    *      ("foo2", "c")
    *    )
    *  }}}
    * @param incoming A map of query parameter keys to sequences of their values.
    * @return A sequence of keys, each paired with exactly one value. The keys are further
    *         normalized to match Flow expectations (e.g. number[] => number)
    */
  def query(
             incoming: Map[String, Seq[String]]
           ): Seq[(String, String)] = {
    Util.toFlatSeq(
      FormData.parseEncoded(FormData.toEncoded(FormData.toJson(incoming)))
    )
  }
}

class ServerProxyModule extends AbstractModule {
  def configure(): Unit = {
    install(new FactoryModuleBuilder()
      .implement(classOf[ServerProxy], classOf[ServerProxyImpl])
      .build(classOf[ServerProxy.Factory])
    )
  }
}

class ServerProxyImpl @Inject()(
  @javax.inject.Named("metric-actor") val actor: akka.actor.ActorRef,
  implicit val system: ActorSystem,
  urlFormEncodedHandler: handlers.UrlFormEncodedHandler,
  applicationJsonHandler: handlers.ApplicationJsonHandler,
  jsonpHandler: handlers.JsonpHandler,
  genericHandler: handlers.GenericHandler,
  val controllerComponents: ControllerComponents,
  @Assisted override val server: Server
) extends ServerProxy
  with BaseControllerHelpers
{

  private[this] implicit val (ec, name) = resolveContextName(server.name)
  private[this] implicit val materializer: ActorMaterializer = ActorMaterializer()

  /**
    * Returns the execution context to use, if found. Works by recursively
    * shortening service name by splitting on "-"
    */
  @tailrec
  private[this] def resolveContextName(name: String): (ExecutionContext, String) = {
    val contextName = s"$name-context"
    Try {
      system.dispatchers.lookup(contextName)
    } match {
      case Success(context) => {
        Logger.info(s"ServerProxy[${server.name}] using configured execution context[$contextName]")
        (context, name)
      }

      case Failure(_) => {
        val i = name.lastIndexOf("-")
        if (i > 0) {
          resolveContextName(name.substring(0, i))
        } else {
          Logger.warn(s"ServerProxy[${server.name}] execution context[${name}] not found - using ${ServerProxy.DefaultContextName}")
          (system.dispatchers.lookup(ServerProxy.DefaultContextName), ServerProxy.DefaultContextName)
        }
      }
    }
  }

  override final def proxy(
    request: ProxyRequest,
    route: Route,
    token: ResolvedToken,
    organization: Option[String] = None,
    partner: Option[String] = None
  ): Future[Result] = {
    Logger.info(s"[proxy $request] to [${server.name}] ${route.method} ${server.host}${request.path}")

    if (request.jsonpCallback.isDefined) {
      jsonpHandler.process(server, request, route, token)
    } else {
      request.contentType match {
        case ContentType.UrlFormEncoded => {
          urlFormEncodedHandler.process(server, request, route, token)
        }

        case ContentType.ApplicationJson => {
          applicationJsonHandler.process(server, request, route, token)
        }

        case _ => {
          genericHandler.process(server, request, route, token)
        }
      }
    }
  }

}
