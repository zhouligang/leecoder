FROM openjdk:8-jre-alpine

VOLUME /.halo

ARG JAR_FILE=./target/halo.jar
ARG PORT=9000
ARG TIME_ZONE=Asia/Shanghai

COPY ${JAR_FILE} halo.jar

ENV TZ=${TIME_ZONE}

EXPOSE ${PORT}

ENTRYPOINT ["java", "-Xmx512m", "-jar", "halo.jar"]

