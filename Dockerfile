FROM maven:3.8.5-openjdk-17 AS build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY /src /src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build /target/*.jar application.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "application.jar"]