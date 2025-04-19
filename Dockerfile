FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the JAR file from the target directory
COPY target/pokedex-api-0.0.1-SNAPSHOT.jar app.jar


# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
