# Use a base image with Maven and JDK 17 for building the application
FROM maven:3.8.5-openjdk-17 AS builder
# Set the working directory inside the container
WORKDIR /app
# Copy the project files into the container
COPY . /app
# Build the Spring Boot application using Maven
RUN mvn clean install -DskipTests #Skipping tests to speed up the docker image build

# Use a base image with only the JDK 17 for running the application
FROM openjdk:17-jdk-slim
# Set the working directory for the runtime environment
WORKDIR /app
# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar
# Expose the port that the Spring Boot application runs on (default: 8080)
EXPOSE 8080
# Set the JAVA_HOME environment variable
ENV JAVA_HOME=/usr/local/openjdk-17
# Specify the command to run the Spring Boot application
ENTRYPOINT ["$JAVA_HOME/bin/java", "-jar", "app.jar"]