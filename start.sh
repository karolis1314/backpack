#!/bin/bash
echo "Cleaning and building the project..."
./mvnw clean install

echo "Building the Docker image..."
docker build -t backpack-api .

echo "Running the Docker container..."
docker run -d -p 8080:8080 --name backpack-service backpack-api

echo "Your Docker container is now running. Just give it a few seconds to start up. You can access it at http://localhost:8080/swagger-ui/index.html"
