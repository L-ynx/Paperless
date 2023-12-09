FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /usr/src/app
COPY ./pom.xml .
RUN mvn -B -f ./pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:go-offline -DskipTests
COPY ./ .
RUN mvn -B -f ./pom.xml -s /usr/share/maven/ref/settings-docker.xml package -DskipTests -DoutputDirectory=target

FROM openjdk:17-jdk-alpine
WORKDIR /app

RUN apk add --no-cache tesseract-ocr

RUN mkdir -p /usr/share/tessdata
ADD https://github.com/tesseract-ocr/tessdata_best/raw/main/eng.traineddata /usr/share/tessdata/

# Check the installation status
RUN tesseract --list-langs
RUN tesseract -v

# Copy the JAR file from the build stage to the final image
COPY --from=build /usr/src/app/target/PaperlessServices-1.0.jar PaperlessServices.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "PaperlessServices.jar"]
