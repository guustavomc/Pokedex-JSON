Pokedex Application Deployment with Docker, Kind, and Helm

1. Ensure the JAR Build Process Works

Before deploying, ensure your Java application builds successfully:

    mvn clean package

2. Containerizing the App with Docker

First, we package our Java application into a Docker container.

    docker build -t my-pokedex-app .

Test if the container runs properly:

    docker run -it --rm my-pokedex-app

3. Setting Up a Kubernetes Cluster with Kind

Since setting up a full Kubernetes cluster is complex, we use Kind (Kubernetes in Docker) to create a local cluster:

    kind create cluster --name my-cluster

Check if the cluster is running:

    kubectl cluster-info --context kind-my-cluster

4. Loading the Docker Image into Kind

By default, Kubernetes pulls images from a registry, but since our image is local, we need to manually load it into Kind:

    kind load docker-image my-pokedex-app:latest --name my-cluster

5. Deploying the Application Manually

Now, we need to define how Kubernetes should run our app using YAML configuration files.

Create Kubernetes Resources

Deployment (deployment.yaml) → Specifies how the app should run.

Service (service.yaml) → Exposes the app externally.

Apply them to your Kind cluster:

    kubectl apply -f deployment.yaml
    kubectl apply -f service.yaml

6. Automating Deployment with Helm

Instead of manually applying YAML files, we use Helm to package everything into a Helm Chart.

Create a Helm Chart

    helm create pokedex-chart

Modify values.yaml to configure the deployment. Then install the chart:

    helm install pokedex-app ./pokedex-chart

7. Checking if the Deployment Works

Verify if the pods are running:

    kubectl get pods

Find the NodePort assigned to the service:

    kubectl get svc pokedex-service

Access the application via:

    http://localhost:<NodePort>


What We Accomplished and Why

Docker: Containerizing the App
✅ Created a self-contained Java application that runs consistently anywhere.

Kind: Creating a Local Kubernetes Cluster
✅ Provided a mini Kubernetes environment for testing before deployment.

Loading Docker Image into Kind
✅ Allowed Kubernetes to recognize our locally built image.

Deploying Manually with YAML Files
✅ Ensured Kubernetes knows how to run and expose the app.

Automating Deployment with Helm
✅ Made the deployment easier, reusable, and updatable.

Checking the Deployment
✅ Verified the app runs correctly and is accessible.

Step                Without Kind & Helm                         With Kind & Helm

Testing Locally     Need a full K8s setup or cloud cluster.     Simple local Kubernetes testing.

Deploying the App   Manually create YAML files and apply them.  Use Helm to deploy everything in one step.

Updating the App    Manually edit YAML files and reapply.       Just update values.yaml and redeploy.

With Docker, Kind, and Helm, we:
✅ Containerized our Java app.
✅ Deployed it to a local Kubernetes cluster.
✅ Automated the deployment using Helm.
✅ Tested everything to ensure it worked.

This setup allows for an efficient, scalable, and repeatable deployment process!

