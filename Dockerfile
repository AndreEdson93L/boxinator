FROM gradle:jdk17-alpine AS build
WORKDIR /app
COPY . .
RUN gradle bootJar

FROM openjdk:17-jdk-alpine AS runtime
WORKDIR /app

ENV SPRING_ACTIVE_PROFILES=prod
ENV MY_MESSAGE=its_docker

EXPOSE 8080
