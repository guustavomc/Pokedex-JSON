FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the JAR file from the target directory
COPY target/Pokedex-JSON-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

# Copy the pokedex.json file into the container
COPY pokedex.json /app/pokedex.json

# Run the application
CMD ["java", "-jar", "app.jar"]
