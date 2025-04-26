# Use a base image with Maven and JDK 21 for building the application
FROM maven:3.9.5-amazoncorretto-21 AS builder
# Set the working directory inside the container
WORKDIR /app
# Copy the project files into the container
COPY . /app
# Build the Spring Boot application using Maven
RUN mvn clean install -DskipTests

# Use a base image with only the JDK 21 for running the application
FROM amazoncorretto:21-alpine-jdk
# Set the working directory for the runtime environment
WORKDIR /app
# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar
# Expose the port that the Spring Boot application runs on (default: 8080)
EXPOSE 8080
# Specify the command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
