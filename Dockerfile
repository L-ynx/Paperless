# Stage 1: Build the application with Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /usr/src/app

# Kopiere nur die POM-Dateien, um die Abhängigkeiten zu installieren
COPY ./PaperlessDB/pom.xml ./PaperlessDB/pom.xml
COPY ./PaperlessRest/pom.xml ./PaperlessRest/
COPY ./pom.xml .
RUN mvn -X -B -s /usr/share/maven/ref/settings-docker.xml dependency:go-offline

# Kopiere den Rest des Projekts und erstelle das finale Artefakt
COPY ./ .

RUN mvn -B -s /usr/share/maven/ref/settings-docker.xml package

# Stage 2: Create the final image with the application
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /usr/src/app/PaperlessRest/target/PaperlessRest-1.0-SNAPSHOT.jar PaperlessRest.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "PaperlessRest.jar"]