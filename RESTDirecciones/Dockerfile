FROM openjdk:11
VOLUME /tmp
EXPOSE 8002
ADD ./target/RESTDirecciones-1.0.jar rest-direcciones.jar
ENTRYPOINT [ "java", "-jar", "/rest-direcciones.jar" ]