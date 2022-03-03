FROM openjdk:11
VOLUME /tmp
EXPOSE 8090
ADD ./target/GatewayServer-1.0.jar gateway-server.jar
ENTRYPOINT [ "java", "-jar", "/gateway-server.jar" ]