#!/bin/bash

#VERSION=$(ls build/libs | sed 's/.*halo-//' | sed 's/.jar$//')
#
#echo "Halo version: $VERSION"

#docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
#docker build -f Dockerfile .
#docker build --build-arg JAR_FILE="./target/halo.jar" -t $DOCKER_USERNAME/halo .
docker build -t leecoder .
docker run -d -p 9000:9000 leecoder
#docker tag $DOCKER_USERNAME/halo $DOCKER_USERNAME/halo:latest
#docker push $DOCKER_USERNAME/halo:latest