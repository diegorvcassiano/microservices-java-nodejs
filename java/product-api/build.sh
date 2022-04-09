#!/bin/sh
# Build the project using maven
./mvnw clean package -DskipTests

# Generate the Docker image - Here we are using podman instead of docker. 
podman build -t product-api .

# Clean the package
./mvnw clean