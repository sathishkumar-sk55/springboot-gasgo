FROM openjdk:23-jdk
ADD target/async-handler.jar async-handler.jar
ENTRYPOINT ["java","-jar","/async-handler.jar","--spring.profiles.active=docker"]
