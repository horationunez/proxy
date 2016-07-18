FROM flowdocker/play:0.0.60

ADD . /opt/play

WORKDIR /opt/play

RUN sbt clean stage

WORKDIR target/universal/stage

ENTRYPOINT ["java", "-jar", "/root/environment-provider.jar", "--service", "play", "proxy", "bin/proxy"]
