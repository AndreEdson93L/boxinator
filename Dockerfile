# Build stage
FROM gradle:jdk17-alpine AS build
WORKDIR /app
COPY . .
RUN gradle bootJar

# Runtime stage
FROM openjdk:17 AS runtime
WORKDIR /app

ENV SPRING_ACTIVE_PROFILES "prod"
ENV MY_MESSAGE "its docker"

EXPOSE 8080

COPY --from=build /app/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
