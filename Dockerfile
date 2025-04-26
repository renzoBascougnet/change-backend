# Etapa de build
FROM eclipse-temurin:21 AS build

# Instalamos Maven manualmente
RUN apt-get update && apt-get install -y maven

WORKDIR /app
COPY . .

# Compilamos el proyecto sin ejecutar tests
RUN mvn clean package -DskipTests

# Etapa final
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app
COPY --from=build /app/target/change-backend-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]