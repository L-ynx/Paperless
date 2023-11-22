# Stage 1: Build the application with Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /usr/src/app
COPY ./pom.xml .
RUN mvn -B -f ./pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:go-offline
COPY ./ .
RUN mvn -B -f ./pom.xml -s /usr/share/maven/ref/settings-docker.xml package

# Stage 2: Create the final image with the application
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /usr/src/app/target/Paperless-1.0.jar Paperless.jar
EXPOSE 8081
CMD ["java", "-jar", "Paperless.jar"]