Pokedex Application Deployment with Docker, Kind, and Helm

1. Ensure the JAR Build Process Works

Before deploying, ensure your Java application builds successfully:

    mvn clean package

2. Run java application:

    mvn spring-boot:run


Examples of access using API:

Get Pokemon with ID 1: "http://localhost:8080/api/pokemon/id/1"
Get Pokemon with Name Squirtle: "http://localhost:8080/api/pokemon/squirtle"
    

3. Containerizing the App with Docker

First, we package our Java application into a Docker container.

    docker build -t pokedex-api .

Test if the container runs properly:

    docker run -p 8080:8080 pokedex-api



