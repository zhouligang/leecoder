#!/bin/bash

#VERSION=$(ls build/libs | sed 's/.*halo-//' | sed 's/.jar$//')
#
#echo "Halo version: $VERSION"
CONTAINER_NAME="leecoder"
docker build -t $CONTAINER_NAME .
cid=$(docker ps -a| grep "$CONTAINER_NAME" | awk '{print $1}')
if [ "$cid" != "" ]; then
    echo "--------正在删除容器，ID为：$cid------------------------------"
#   docker stop $cid
   docker rm -f $cid
fi
docker run --rm -it -d --name $CONTAINER_NAME -p 9000:9000  -v ~/.leecoder:/root/.leecoder $CONTAINER_NAME
# 删除指定名称的镜像
#docker images|grep "$CONTAINER_NAME"|awk '{print $3}'|xargs docker rmi
# 删除没有版本号的镜像
docker images|grep "<none>"|awk '{print $3}'|xargs docker rmi

#推送镜像到官方仓库
docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
#docker build -f Dockerfile .
#docker build --build-arg JAR_FILE="./target/halo.jar" -t $DOCKER_USERNAME/halo .
docker tag leecoder $DOCKER_USERNAME/leecoder:latest
docker push $DOCKER_USERNAME/leecoder:latest