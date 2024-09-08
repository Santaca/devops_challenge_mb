FROM openjdk:22

WORKDIR /
COPY ./sw-api/target/sw-api-1.0-SNAPSHOT.jar api.jar

EXPOSE 9090/tcp

ENTRYPOINT ["java", "-jar", "api.jar"]