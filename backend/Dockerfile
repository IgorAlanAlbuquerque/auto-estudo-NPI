# Etapa de build com Maven
FROM maven:3.9.5-eclipse-temurin-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

# Etapa final: apenas o JAR com JDK
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/autoestudo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]

