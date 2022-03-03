FROM openjdk:11
VOLUME /tmp
ADD ./target/RESTFraudes-1.0.jar rest-fraudes.jar
ENTRYPOINT [ "java", "-jar", "/rest-fraudes.jar" ]