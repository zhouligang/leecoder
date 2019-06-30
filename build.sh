#!/bin/bash

#VERSION=$(ls build/libs | sed 's/.*halo-//' | sed 's/.jar$//')
#
#echo "Halo version: $VERSION"
docker build -t leecoder .
docker run --rm -it -d --name leecoder -p 9000:9000  -v ~/.leecoder:/root/.leecoder leecoder
#docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
#docker build -f Dockerfile .
#docker build --build-arg JAR_FILE="./target/halo.jar" -t $DOCKER_USERNAME/halo .
#docker tag leecoder $DOCKER_USERNAME/leecoder:latest
#docker push $DOCKER_USERNAME/leecoder:latest