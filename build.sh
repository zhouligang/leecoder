#!/bin/bash

#VERSION=$(ls build/libs | sed 's/.*halo-//' | sed 's/.jar$//')
#
#echo "Halo version: $VERSION"

docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
docker build --build-arg JAR_FILE="./target/halo.jar" -t $DOCKER_USERNAME/halo .
docker tag $DOCKER_USERNAME/halo $DOCKER_USERNAME/halo:latest
docker push $DOCKER_USERNAME/halo:latest