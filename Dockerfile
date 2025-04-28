FROM eclipse-temurin:21 AS build

RUN apt-get update && apt-get install -y maven

WORKDIR /app
COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app
COPY --from=build /app/target/change-backend-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]