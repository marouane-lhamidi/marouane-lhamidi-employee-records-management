# Stage 1: Build the application
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src src

# Build the application
RUN mvn clean package

# Stage 2: Create the runtime image
FROM openjdk:17-slim
VOLUME /tmp

# Copy the built JAR file
COPY --from=build /app/target/back-employee-records-management.jar back-employee-records-management.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/back-employee-records-management.jar"]
