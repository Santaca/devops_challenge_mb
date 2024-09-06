FROM openjdk:22

WORKDIR /
COPY ./sw-api/target/*.jar api.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "api.jar"]