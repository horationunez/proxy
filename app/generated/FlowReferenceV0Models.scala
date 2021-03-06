/**
 * Generated by API Builder - https://www.apibuilder.io
 * Service version: 0.4.73
 * apibuilder-0.13.0 https://app.apibuilder.io/flow/reference/0.4.73/play_2_x_json
 */
package io.flow.reference.v0.models {

  /**
   * ISO 3166 country codes. Note Flow APIs will accept either the 2 or 3 character
   * country code, but internally we normalize data and store as the 3 character,
   * upper case ISO code. See https://api.flow.io/reference/countries
   * 
   * @param iso31662 ISO 3166 2-character country code. See https://api.flow.io/reference/countries
   * @param iso31663 ISO 3166 3-character country code. See https://api.flow.io/reference/countries
   * @param languages A list of the languages spoken in this country
   * @param defaultCurrency ISO 4217 3-character currency code. See https://api.flow.io/reference/currencies
   * @param timezones A list of canonical timezone IDs for the country. See
   *        http://joda-time.sourceforge.net/timezones.html
   * @param defaultDeliveredDuty Default delivered duty value. See https://en.wikipedia.org/wiki/Incoterms for
   *        more information
   */
  case class Country(
    name: String,
    iso31662: String,
    iso31663: String,
    languages: Seq[String],
    measurementSystem: String,
    defaultCurrency: _root_.scala.Option[String] = None,
    timezones: Seq[String],
    defaultDeliveredDuty: _root_.scala.Option[String] = None
  )

  /**
   * ISO 4217 3-character currency code. See https://api.flow.io/reference/currencies
   * 
   * @param numberDecimals The number of decimal places used by the given currency. For example, USD has 2
   *        decimals while JPY has 0.
   * @param defaultLocale The locale id of the default locale to use when rendering this currency
   */
  case class Currency(
    name: String,
    iso42173: String,
    numberDecimals: Int,
    symbols: _root_.scala.Option[io.flow.reference.v0.models.CurrencySymbols] = None,
    defaultLocale: _root_.scala.Option[String] = None
  )

  /**
   * Defines one or more symbols representing this currency
   */
  case class CurrencySymbols(
    primary: String,
    narrow: _root_.scala.Option[String] = None
  )

  /**
   * ISO 639 2-character language code. See https://api.flow.io/reference/languages
   */
  case class Language(
    name: String,
    iso6392: String
  )

  /**
   * Locales defines standard conventions for presentation of content. See
   * https://api.flow.io/reference/locales
   * 
   * @param country ISO 3166 3 country code
   * @param language ISO 639 2 language code
   */
  case class Locale(
    id: String,
    name: String,
    country: String,
    language: String,
    numbers: io.flow.reference.v0.models.LocaleNumbers
  )

  /**
   * Number formats defined for a given locale
   * 
   * @param decimal Decimal separator
   * @param group Group separator (e.g. 1,000 have a group separator of ',')
   */
  case class LocaleNumbers(
    decimal: String,
    group: String
  )

  /**
   * Represents a single payment method - e.g VISA or Paypal - and any associated
   * metadata required for processing
   * 
   * @param regions List of region ids in which this payment method is available
   */
  case class PaymentMethod(
    id: String,
    `type`: io.flow.reference.v0.models.PaymentMethodType,
    name: String,
    images: io.flow.reference.v0.models.PaymentMethodImages,
    regions: Seq[String]
  )

  case class PaymentMethodImage(
    url: String,
    width: Int,
    height: Int
  )

  case class PaymentMethodImages(
    small: io.flow.reference.v0.models.PaymentMethodImage,
    medium: io.flow.reference.v0.models.PaymentMethodImage,
    large: io.flow.reference.v0.models.PaymentMethodImage
  )

  /**
   * A subdivision/province/state within a country. These conform to the ISO 3166-2
   * standard for country subdivisions. See https://api.flow.io/reference/provinces
   * 
   * @param country ISO 3166 3 code of the country for this subdivision
   */
  case class Province(
    id: String,
    iso31662: String,
    name: String,
    country: String,
    provinceType: io.flow.reference.v0.models.ProvinceType
  )

  /**
   * A region represents a geographic area of the world. Regions can be countries,
   * continents or other political areas (like the Eurozone). See
   * https://api.flow.io/reference/regions
   * 
   * @param countries A list of the countries as ISO 3166 3 codes in this region
   * @param currencies A list of the currencies as ISO 4217 3 codes in this region
   * @param languages A list of the languages as ISO 639 2 codes spoken in this region
   * @param measurementSystems A list of the measurement systems in use in this region (metric or imperial)
   * @param timezones A list of canonical timezone IDs for the region. See
   *        http://joda-time.sourceforge.net/timezones.html
   */
  case class Region(
    id: String,
    name: String,
    countries: Seq[String],
    currencies: Seq[String],
    languages: Seq[String],
    measurementSystems: Seq[String],
    timezones: Seq[String]
  )

  /**
   * Time zone data is provided by the public IANA time zone database. See
   * http://www.iana.org/time-zones
   * 
   * @param offset Minutes offset from GMT
   */
  case class Timezone(
    name: String,
    description: String,
    offset: Int
  )

  /**
   * The payment method type defines at a high level different user experiences that
   * are required to accept payment of this type. By enabling a payment method type,
   * you are specifying that you have completed the integration and all payment
   * methods of this type become available for offer to your users.
   */
  sealed trait PaymentMethodType extends _root_.scala.Product with _root_.scala.Serializable

  object PaymentMethodType {

    /**
     * Represents all form of card payment (e.g. credit, debit, etc.)
     */
    case object Card extends PaymentMethodType { override def toString = "card" }
    /**
     * Represents the most common form of alternative payment methods which require
     * some degree of integration online (e.g. a redirect) to complete payment.
     */
    case object Online extends PaymentMethodType { override def toString = "online" }
    /**
     * Offline payment method types represent payments like Cash On Delivery which
     * require offline collection
     */
    case object Offline extends PaymentMethodType { override def toString = "offline" }

    /**
     * UNDEFINED captures values that are sent either in error or
     * that were added by the server after this library was
     * generated. We want to make it easy and obvious for users of
     * this library to handle this case gracefully.
     *
     * We use all CAPS for the variable name to avoid collisions
     * with the camel cased values above.
     */
    case class UNDEFINED(override val toString: String) extends PaymentMethodType

    /**
     * all returns a list of all the valid, known values. We use
     * lower case to avoid collisions with the camel cased values
     * above.
     */
    val all: scala.List[PaymentMethodType] = scala.List(Card, Online, Offline)

    private[this]
    val byName: Map[String, PaymentMethodType] = all.map(x => x.toString.toLowerCase -> x).toMap

    def apply(value: String): PaymentMethodType = fromString(value).getOrElse(UNDEFINED(value))

    def fromString(value: String): _root_.scala.Option[PaymentMethodType] = byName.get(value.toLowerCase)

  }

  /**
   * Local terminology for province
   */
  sealed trait ProvinceType extends _root_.scala.Product with _root_.scala.Serializable

  object ProvinceType {

    case object City extends ProvinceType { override def toString = "city" }
    case object Dependency extends ProvinceType { override def toString = "dependency" }
    case object District extends ProvinceType { override def toString = "district" }
    case object Emirate extends ProvinceType { override def toString = "emirate" }
    case object Entity extends ProvinceType { override def toString = "entity" }
    case object Municipality extends ProvinceType { override def toString = "municipality" }
    case object OutlyingArea extends ProvinceType { override def toString = "outlying_area" }
    case object Parish extends ProvinceType { override def toString = "parish" }
    case object Province extends ProvinceType { override def toString = "province" }
    case object State extends ProvinceType { override def toString = "state" }
    case object Territory extends ProvinceType { override def toString = "territory" }
    case object Other extends ProvinceType { override def toString = "other" }

    /**
     * UNDEFINED captures values that are sent either in error or
     * that were added by the server after this library was
     * generated. We want to make it easy and obvious for users of
     * this library to handle this case gracefully.
     *
     * We use all CAPS for the variable name to avoid collisions
     * with the camel cased values above.
     */
    case class UNDEFINED(override val toString: String) extends ProvinceType

    /**
     * all returns a list of all the valid, known values. We use
     * lower case to avoid collisions with the camel cased values
     * above.
     */
    val all: scala.List[ProvinceType] = scala.List(City, Dependency, District, Emirate, Entity, Municipality, OutlyingArea, Parish, Province, State, Territory, Other)

    private[this]
    val byName: Map[String, ProvinceType] = all.map(x => x.toString.toLowerCase -> x).toMap

    def apply(value: String): ProvinceType = fromString(value).getOrElse(UNDEFINED(value))

    def fromString(value: String): _root_.scala.Option[ProvinceType] = byName.get(value.toLowerCase)

  }

}

package io.flow.reference.v0.models {

  package object json {
    import play.api.libs.json.__
    import play.api.libs.json.JsString
    import play.api.libs.json.Writes
    import play.api.libs.functional.syntax._
    import io.flow.reference.v0.models.json._

    private[v0] implicit val jsonReadsUUID = __.read[String].map(java.util.UUID.fromString)

    private[v0] implicit val jsonWritesUUID = new Writes[java.util.UUID] {
      def writes(x: java.util.UUID) = JsString(x.toString)
    }

    private[v0] implicit val jsonReadsJodaDateTime = __.read[String].map { str =>
      import org.joda.time.format.ISODateTimeFormat.dateTimeParser
      dateTimeParser.parseDateTime(str)
    }

    private[v0] implicit val jsonWritesJodaDateTime = new Writes[org.joda.time.DateTime] {
      def writes(x: org.joda.time.DateTime) = {
        import org.joda.time.format.ISODateTimeFormat.dateTime
        val str = dateTime.print(x)
        JsString(str)
      }
    }

    private[v0] implicit val jsonReadsJodaLocalDate = __.read[String].map { str =>
      import org.joda.time.format.ISODateTimeFormat.dateParser
      dateParser.parseLocalDate(str)
    }

    private[v0] implicit val jsonWritesJodaLocalDate = new Writes[org.joda.time.LocalDate] {
      def writes(x: org.joda.time.LocalDate) = {
        import org.joda.time.format.ISODateTimeFormat.date
        val str = date.print(x)
        JsString(str)
      }
    }

    implicit val jsonReadsReferencePaymentMethodType = new play.api.libs.json.Reads[io.flow.reference.v0.models.PaymentMethodType] {
      def reads(js: play.api.libs.json.JsValue): play.api.libs.json.JsResult[io.flow.reference.v0.models.PaymentMethodType] = {
        js match {
          case v: play.api.libs.json.JsString => play.api.libs.json.JsSuccess(io.flow.reference.v0.models.PaymentMethodType(v.value))
          case _ => {
            (js \ "value").validate[String] match {
              case play.api.libs.json.JsSuccess(v, _) => play.api.libs.json.JsSuccess(io.flow.reference.v0.models.PaymentMethodType(v))
              case err: play.api.libs.json.JsError => err
            }
          }
        }
      }
    }

    def jsonWritesReferencePaymentMethodType(obj: io.flow.reference.v0.models.PaymentMethodType) = {
      play.api.libs.json.JsString(obj.toString)
    }

    def jsObjectPaymentMethodType(obj: io.flow.reference.v0.models.PaymentMethodType) = {
      play.api.libs.json.Json.obj("value" -> play.api.libs.json.JsString(obj.toString))
    }

    implicit def jsonWritesReferencePaymentMethodType: play.api.libs.json.Writes[PaymentMethodType] = {
      new play.api.libs.json.Writes[io.flow.reference.v0.models.PaymentMethodType] {
        def writes(obj: io.flow.reference.v0.models.PaymentMethodType) = {
          jsonWritesReferencePaymentMethodType(obj)
        }
      }
    }

    implicit val jsonReadsReferenceProvinceType = new play.api.libs.json.Reads[io.flow.reference.v0.models.ProvinceType] {
      def reads(js: play.api.libs.json.JsValue): play.api.libs.json.JsResult[io.flow.reference.v0.models.ProvinceType] = {
        js match {
          case v: play.api.libs.json.JsString => play.api.libs.json.JsSuccess(io.flow.reference.v0.models.ProvinceType(v.value))
          case _ => {
            (js \ "value").validate[String] match {
              case play.api.libs.json.JsSuccess(v, _) => play.api.libs.json.JsSuccess(io.flow.reference.v0.models.ProvinceType(v))
              case err: play.api.libs.json.JsError => err
            }
          }
        }
      }
    }

    def jsonWritesReferenceProvinceType(obj: io.flow.reference.v0.models.ProvinceType) = {
      play.api.libs.json.JsString(obj.toString)
    }

    def jsObjectProvinceType(obj: io.flow.reference.v0.models.ProvinceType) = {
      play.api.libs.json.Json.obj("value" -> play.api.libs.json.JsString(obj.toString))
    }

    implicit def jsonWritesReferenceProvinceType: play.api.libs.json.Writes[ProvinceType] = {
      new play.api.libs.json.Writes[io.flow.reference.v0.models.ProvinceType] {
        def writes(obj: io.flow.reference.v0.models.ProvinceType) = {
          jsonWritesReferenceProvinceType(obj)
        }
      }
    }

    implicit def jsonReadsReferenceCountry: play.api.libs.json.Reads[Country] = {
      (
        (__ \ "name").read[String] and
        (__ \ "iso_3166_2").read[String] and
        (__ \ "iso_3166_3").read[String] and
        (__ \ "languages").read[Seq[String]] and
        (__ \ "measurement_system").read[String] and
        (__ \ "default_currency").readNullable[String] and
        (__ \ "timezones").read[Seq[String]] and
        (__ \ "default_delivered_duty").readNullable[String]
      )(Country.apply _)
    }

    def jsObjectCountry(obj: io.flow.reference.v0.models.Country): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "name" -> play.api.libs.json.JsString(obj.name),
        "iso_3166_2" -> play.api.libs.json.JsString(obj.iso31662),
        "iso_3166_3" -> play.api.libs.json.JsString(obj.iso31663),
        "languages" -> play.api.libs.json.Json.toJson(obj.languages),
        "measurement_system" -> play.api.libs.json.JsString(obj.measurementSystem),
        "timezones" -> play.api.libs.json.Json.toJson(obj.timezones)
      ) ++ (obj.defaultCurrency match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("default_currency" -> play.api.libs.json.JsString(x))
      }) ++
      (obj.defaultDeliveredDuty match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("default_delivered_duty" -> play.api.libs.json.JsString(x))
      })
    }

    implicit def jsonWritesReferenceCountry: play.api.libs.json.Writes[Country] = {
      new play.api.libs.json.Writes[io.flow.reference.v0.models.Country] {
        def writes(obj: io.flow.reference.v0.models.Country) = {
          jsObjectCountry(obj)
        }
      }
    }

    implicit def jsonReadsReferenceCurrency: play.api.libs.json.Reads[Currency] = {
      (
        (__ \ "name").read[String] and
        (__ \ "iso_4217_3").read[String] and
        (__ \ "number_decimals").read[Int] and
        (__ \ "symbols").readNullable[io.flow.reference.v0.models.CurrencySymbols] and
        (__ \ "default_locale").readNullable[String]
      )(Currency.apply _)
    }

    def jsObjectCurrency(obj: io.flow.reference.v0.models.Currency): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "name" -> play.api.libs.json.JsString(obj.name),
        "iso_4217_3" -> play.api.libs.json.JsString(obj.iso42173),
        "number_decimals" -> play.api.libs.json.JsNumber(obj.numberDecimals)
      ) ++ (obj.symbols match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("symbols" -> jsObjectCurrencySymbols(x))
      }) ++
      (obj.defaultLocale match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("default_locale" -> play.api.libs.json.JsString(x))
      })
    }

    implicit def jsonWritesReferenceCurrency: play.api.libs.json.Writes[Currency] = {
      new play.api.libs.json.Writes[io.flow.reference.v0.models.Currency] {
        def writes(obj: io.flow.reference.v0.models.Currency) = {
          jsObjectCurrency(obj)
        }
      }
    }

    implicit def jsonReadsReferenceCurrencySymbols: play.api.libs.json.Reads[CurrencySymbols] = {
      (
        (__ \ "primary").read[String] and
        (__ \ "narrow").readNullable[String]
      )(CurrencySymbols.apply _)
    }

    def jsObjectCurrencySymbols(obj: io.flow.reference.v0.models.CurrencySymbols): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "primary" -> play.api.libs.json.JsString(obj.primary)
      ) ++ (obj.narrow match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("narrow" -> play.api.libs.json.JsString(x))
      })
    }

    implicit def jsonWritesReferenceCurrencySymbols: play.api.libs.json.Writes[CurrencySymbols] = {
      new play.api.libs.json.Writes[io.flow.reference.v0.models.CurrencySymbols] {
        def writes(obj: io.flow.reference.v0.models.CurrencySymbols) = {
          jsObjectCurrencySymbols(obj)
        }
      }
    }

    implicit def jsonReadsReferenceLanguage: play.api.libs.json.Reads[Language] = {
      (
        (__ \ "name").read[String] and
        (__ \ "iso_639_2").read[String]
      )(Language.apply _)
    }

    def jsObjectLanguage(obj: io.flow.reference.v0.models.Language): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "name" -> play.api.libs.json.JsString(obj.name),
        "iso_639_2" -> play.api.libs.json.JsString(obj.iso6392)
      )
    }

    implicit def jsonWritesReferenceLanguage: play.api.libs.json.Writes[Language] = {
      new play.api.libs.json.Writes[io.flow.reference.v0.models.Language] {
        def writes(obj: io.flow.reference.v0.models.Language) = {
          jsObjectLanguage(obj)
        }
      }
    }

    implicit def jsonReadsReferenceLocale: play.api.libs.json.Reads[Locale] = {
      (
        (__ \ "id").read[String] and
        (__ \ "name").read[String] and
        (__ \ "country").read[String] and
        (__ \ "language").read[String] and
        (__ \ "numbers").read[io.flow.reference.v0.models.LocaleNumbers]
      )(Locale.apply _)
    }

    def jsObjectLocale(obj: io.flow.reference.v0.models.Locale): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "id" -> play.api.libs.json.JsString(obj.id),
        "name" -> play.api.libs.json.JsString(obj.name),
        "country" -> play.api.libs.json.JsString(obj.country),
        "language" -> play.api.libs.json.JsString(obj.language),
        "numbers" -> jsObjectLocaleNumbers(obj.numbers)
      )
    }

    implicit def jsonWritesReferenceLocale: play.api.libs.json.Writes[Locale] = {
      new play.api.libs.json.Writes[io.flow.reference.v0.models.Locale] {
        def writes(obj: io.flow.reference.v0.models.Locale) = {
          jsObjectLocale(obj)
        }
      }
    }

    implicit def jsonReadsReferenceLocaleNumbers: play.api.libs.json.Reads[LocaleNumbers] = {
      (
        (__ \ "decimal").read[String] and
        (__ \ "group").read[String]
      )(LocaleNumbers.apply _)
    }

    def jsObjectLocaleNumbers(obj: io.flow.reference.v0.models.LocaleNumbers): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "decimal" -> play.api.libs.json.JsString(obj.decimal),
        "group" -> play.api.libs.json.JsString(obj.group)
      )
    }

    implicit def jsonWritesReferenceLocaleNumbers: play.api.libs.json.Writes[LocaleNumbers] = {
      new play.api.libs.json.Writes[io.flow.reference.v0.models.LocaleNumbers] {
        def writes(obj: io.flow.reference.v0.models.LocaleNumbers) = {
          jsObjectLocaleNumbers(obj)
        }
      }
    }

    implicit def jsonReadsReferencePaymentMethod: play.api.libs.json.Reads[PaymentMethod] = {
      (
        (__ \ "id").read[String] and
        (__ \ "type").read[io.flow.reference.v0.models.PaymentMethodType] and
        (__ \ "name").read[String] and
        (__ \ "images").read[io.flow.reference.v0.models.PaymentMethodImages] and
        (__ \ "regions").read[Seq[String]]
      )(PaymentMethod.apply _)
    }

    def jsObjectPaymentMethod(obj: io.flow.reference.v0.models.PaymentMethod): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "id" -> play.api.libs.json.JsString(obj.id),
        "type" -> play.api.libs.json.JsString(obj.`type`.toString),
        "name" -> play.api.libs.json.JsString(obj.name),
        "images" -> jsObjectPaymentMethodImages(obj.images),
        "regions" -> play.api.libs.json.Json.toJson(obj.regions)
      )
    }

    implicit def jsonWritesReferencePaymentMethod: play.api.libs.json.Writes[PaymentMethod] = {
      new play.api.libs.json.Writes[io.flow.reference.v0.models.PaymentMethod] {
        def writes(obj: io.flow.reference.v0.models.PaymentMethod) = {
          jsObjectPaymentMethod(obj)
        }
      }
    }

    implicit def jsonReadsReferencePaymentMethodImage: play.api.libs.json.Reads[PaymentMethodImage] = {
      (
        (__ \ "url").read[String] and
        (__ \ "width").read[Int] and
        (__ \ "height").read[Int]
      )(PaymentMethodImage.apply _)
    }

    def jsObjectPaymentMethodImage(obj: io.flow.reference.v0.models.PaymentMethodImage): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "url" -> play.api.libs.json.JsString(obj.url),
        "width" -> play.api.libs.json.JsNumber(obj.width),
        "height" -> play.api.libs.json.JsNumber(obj.height)
      )
    }

    implicit def jsonWritesReferencePaymentMethodImage: play.api.libs.json.Writes[PaymentMethodImage] = {
      new play.api.libs.json.Writes[io.flow.reference.v0.models.PaymentMethodImage] {
        def writes(obj: io.flow.reference.v0.models.PaymentMethodImage) = {
          jsObjectPaymentMethodImage(obj)
        }
      }
    }

    implicit def jsonReadsReferencePaymentMethodImages: play.api.libs.json.Reads[PaymentMethodImages] = {
      (
        (__ \ "small").read[io.flow.reference.v0.models.PaymentMethodImage] and
        (__ \ "medium").read[io.flow.reference.v0.models.PaymentMethodImage] and
        (__ \ "large").read[io.flow.reference.v0.models.PaymentMethodImage]
      )(PaymentMethodImages.apply _)
    }

    def jsObjectPaymentMethodImages(obj: io.flow.reference.v0.models.PaymentMethodImages): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "small" -> jsObjectPaymentMethodImage(obj.small),
        "medium" -> jsObjectPaymentMethodImage(obj.medium),
        "large" -> jsObjectPaymentMethodImage(obj.large)
      )
    }

    implicit def jsonWritesReferencePaymentMethodImages: play.api.libs.json.Writes[PaymentMethodImages] = {
      new play.api.libs.json.Writes[io.flow.reference.v0.models.PaymentMethodImages] {
        def writes(obj: io.flow.reference.v0.models.PaymentMethodImages) = {
          jsObjectPaymentMethodImages(obj)
        }
      }
    }

    implicit def jsonReadsReferenceProvince: play.api.libs.json.Reads[Province] = {
      (
        (__ \ "id").read[String] and
        (__ \ "iso_3166_2").read[String] and
        (__ \ "name").read[String] and
        (__ \ "country").read[String] and
        (__ \ "province_type").read[io.flow.reference.v0.models.ProvinceType]
      )(Province.apply _)
    }

    def jsObjectProvince(obj: io.flow.reference.v0.models.Province): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "id" -> play.api.libs.json.JsString(obj.id),
        "iso_3166_2" -> play.api.libs.json.JsString(obj.iso31662),
        "name" -> play.api.libs.json.JsString(obj.name),
        "country" -> play.api.libs.json.JsString(obj.country),
        "province_type" -> play.api.libs.json.JsString(obj.provinceType.toString)
      )
    }

    implicit def jsonWritesReferenceProvince: play.api.libs.json.Writes[Province] = {
      new play.api.libs.json.Writes[io.flow.reference.v0.models.Province] {
        def writes(obj: io.flow.reference.v0.models.Province) = {
          jsObjectProvince(obj)
        }
      }
    }

    implicit def jsonReadsReferenceRegion: play.api.libs.json.Reads[Region] = {
      (
        (__ \ "id").read[String] and
        (__ \ "name").read[String] and
        (__ \ "countries").read[Seq[String]] and
        (__ \ "currencies").read[Seq[String]] and
        (__ \ "languages").read[Seq[String]] and
        (__ \ "measurement_systems").read[Seq[String]] and
        (__ \ "timezones").read[Seq[String]]
      )(Region.apply _)
    }

    def jsObjectRegion(obj: io.flow.reference.v0.models.Region): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "id" -> play.api.libs.json.JsString(obj.id),
        "name" -> play.api.libs.json.JsString(obj.name),
        "countries" -> play.api.libs.json.Json.toJson(obj.countries),
        "currencies" -> play.api.libs.json.Json.toJson(obj.currencies),
        "languages" -> play.api.libs.json.Json.toJson(obj.languages),
        "measurement_systems" -> play.api.libs.json.Json.toJson(obj.measurementSystems),
        "timezones" -> play.api.libs.json.Json.toJson(obj.timezones)
      )
    }

    implicit def jsonWritesReferenceRegion: play.api.libs.json.Writes[Region] = {
      new play.api.libs.json.Writes[io.flow.reference.v0.models.Region] {
        def writes(obj: io.flow.reference.v0.models.Region) = {
          jsObjectRegion(obj)
        }
      }
    }

    implicit def jsonReadsReferenceTimezone: play.api.libs.json.Reads[Timezone] = {
      (
        (__ \ "name").read[String] and
        (__ \ "description").read[String] and
        (__ \ "offset").read[Int]
      )(Timezone.apply _)
    }

    def jsObjectTimezone(obj: io.flow.reference.v0.models.Timezone): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "name" -> play.api.libs.json.JsString(obj.name),
        "description" -> play.api.libs.json.JsString(obj.description),
        "offset" -> play.api.libs.json.JsNumber(obj.offset)
      )
    }

    implicit def jsonWritesReferenceTimezone: play.api.libs.json.Writes[Timezone] = {
      new play.api.libs.json.Writes[io.flow.reference.v0.models.Timezone] {
        def writes(obj: io.flow.reference.v0.models.Timezone) = {
          jsObjectTimezone(obj)
        }
      }
    }
  }
}

package io.flow.reference.v0 {

  object Bindables {

    import play.api.mvc.{PathBindable, QueryStringBindable}
    import org.joda.time.{DateTime, LocalDate}
    import org.joda.time.format.ISODateTimeFormat
    import io.flow.reference.v0.models._

    // Type: date-time-iso8601
    implicit val pathBindableTypeDateTimeIso8601 = new PathBindable.Parsing[org.joda.time.DateTime](
      ISODateTimeFormat.dateTimeParser.parseDateTime(_), _.toString, (key: String, e: _root_.java.lang.Exception) => s"Error parsing date time $key. Example: 2014-04-29T11:56:52Z"
    )

    implicit val queryStringBindableTypeDateTimeIso8601 = new QueryStringBindable.Parsing[org.joda.time.DateTime](
      ISODateTimeFormat.dateTimeParser.parseDateTime(_), _.toString, (key: String, e: _root_.java.lang.Exception) => s"Error parsing date time $key. Example: 2014-04-29T11:56:52Z"
    )

    // Type: date-iso8601
    implicit val pathBindableTypeDateIso8601 = new PathBindable.Parsing[org.joda.time.LocalDate](
      ISODateTimeFormat.yearMonthDay.parseLocalDate(_), _.toString, (key: String, e: _root_.java.lang.Exception) => s"Error parsing date $key. Example: 2014-04-29"
    )

    implicit val queryStringBindableTypeDateIso8601 = new QueryStringBindable.Parsing[org.joda.time.LocalDate](
      ISODateTimeFormat.yearMonthDay.parseLocalDate(_), _.toString, (key: String, e: _root_.java.lang.Exception) => s"Error parsing date $key. Example: 2014-04-29"
    )

    // Enum: PaymentMethodType
    private[this] val enumPaymentMethodTypeNotFound = (key: String, e: _root_.java.lang.Exception) => s"Unrecognized $key, should be one of ${io.flow.reference.v0.models.PaymentMethodType.all.mkString(", ")}"

    implicit val pathBindableEnumPaymentMethodType = new PathBindable.Parsing[io.flow.reference.v0.models.PaymentMethodType] (
      PaymentMethodType.fromString(_).get, _.toString, enumPaymentMethodTypeNotFound
    )

    implicit val queryStringBindableEnumPaymentMethodType = new QueryStringBindable.Parsing[io.flow.reference.v0.models.PaymentMethodType](
      PaymentMethodType.fromString(_).get, _.toString, enumPaymentMethodTypeNotFound
    )

    // Enum: ProvinceType
    private[this] val enumProvinceTypeNotFound = (key: String, e: _root_.java.lang.Exception) => s"Unrecognized $key, should be one of ${io.flow.reference.v0.models.ProvinceType.all.mkString(", ")}"

    implicit val pathBindableEnumProvinceType = new PathBindable.Parsing[io.flow.reference.v0.models.ProvinceType] (
      ProvinceType.fromString(_).get, _.toString, enumProvinceTypeNotFound
    )

    implicit val queryStringBindableEnumProvinceType = new QueryStringBindable.Parsing[io.flow.reference.v0.models.ProvinceType](
      ProvinceType.fromString(_).get, _.toString, enumProvinceTypeNotFound
    )

  }

}
