#FROM maven:3.8.5-openjdk-17 AS build
#
#WORKDIR /usr/src/app
#COPY ./pom.xml .
#RUN mvn -B -f ./pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve
#COPY ./ .
#RUN mvn -B -f ./pom.xml -s /usr/share/maven/ref/settings-docker.xml package
#
##
##RUN mkdir /app
##COPY . /app
##WORKDIR /app
##RUN mvn -B -f ./pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve
##RUN mvn clean package -f /app/pom.xml
#
#FROM openjdk:17-jdk-alpine
#COPY --from=build app/target/openapi-spring-1.0.jar openapi-spring-1.0.jar
#EXPOSE 8080
#CMD "java" "-jar" "/openapi-spring-1.0.jar"

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
COPY --from=build /usr/src/app/target/openapi-spring-1.0.jar openapi-spring-1.0.jar
EXPOSE 8080
CMD ["java", "-jar", "openapi-spring-1.0.jar"]