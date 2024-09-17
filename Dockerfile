FROM maven:3.9.9-amazoncorretto-17-al2023 AS build

COPY pom.xml app/pom.xml
COPY .mvn app/.mvn
COPY src app/src

WORKDIR /app
RUN mvn clean install

FROM amazoncorretto:17.0.12-al2023

COPY --from=build app/src/main/resources/serviceAccountKey.json /src/main/resources/serviceAccountKey.json

COPY --from=build app/target/*.jar /app/gelecekbilimde-be.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/gelecekbilimde-be.jar"]
