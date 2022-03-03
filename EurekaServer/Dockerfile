FROM openjdk:11
VOLUME /tmp
EXPOSE 8761
ADD ./target/EurekaServer-1.0.jar eureka-server.jar
ENTRYPOINT [ "java", "-jar", "/eureka-server.jar" ]