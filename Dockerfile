FROM openjdk:8-jre-alpine
#FROM maven:3.5-jdk-8-onbuild

VOLUME ./target

ARG JAR_FILE=./target/halo.jar
ARG PORT=9000
ARG TIME_ZONE=Asia/Shanghai

COPY ${JAR_FILE} halo.jar

ENV TZ=${TIME_ZONE}

EXPOSE ${PORT}

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Xmx512m", "-jar", "halo.jar"]

