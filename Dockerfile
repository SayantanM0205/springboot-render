FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY . /app
RUN mvn clean install -DskipTests \
    -Dmaven.compiler.source=17 \
    -Dmaven.compiler.target=17
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENV JAVA_HOME=/usr/local/openjdk-17
ENTRYPOINT ["$JAVA_HOME/bin/java", "-jar", "app.jar"]