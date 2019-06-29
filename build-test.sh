#!/bin/bash
appName="halo"
#word="1"
#echo "$word"
#word=`docker ps -a -q --no-trunc --filter name=^/"$appName"$` echo "$word"
#echo "$word"
#if [ -z "$word" ];
#then
#    echo "当前不存在该容器，直接进行启动该操作-------------------------------------"
#elif [ -n "$word" ];
#then
#    echo "当前已存在容器，停止并移除该容器-------------------------------------"
#    docker stop "$word"
#    docker rm "$word"
#elif [ "$word" == "1" ];
#then
#    echo "查询的信息有误，执行默认操作-------------------------------------"
#    docker stop "$word"
#    docker rm "$word"
#fi
##貌似这样才可以删除容器
cid=$(docker ps -a| grep "$CONTAINER_NAME" | awk '{print $1}')
if [ "$cid" != "" ]; then
    echo "--------删除容器-------$cid------------------------------"
   docker rm -f $cid
fi
# 删除没有版本号的镜像(看需求,可删可不删)
docker images|grep "$appName"|awk '{print $3}'|xargs docker rmi
#删除镜像
echo "--------删除镜像-------------------------------------"
docker images | grep "$appName"  | grep 'latest' | awk '{print $3}' | xargs docker rmi
#运行
docker run -p 9000:9000 -d  "$appName"
echo "--------构建成功-------------------------------------"