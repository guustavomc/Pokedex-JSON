Pokedex Application Deployment with Docker, Kind, and Helm

1. Ensure the JAR Build Process Works

Before deploying, ensure your Java application builds successfully:

    mvn clean package

2. Containerizing the App with Docker

First, we package our Java application into a Docker container.

    docker build -t my-pokedex-app .

Test if the container runs properly:

    docker run -it --rm my-pokedex-app


