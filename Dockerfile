#using openjdk 10 as base image
FROM openjdk:10
COPY ./target/phoneApp-0.0.1-SNAPSHOT.jar /usr/src/phoneApp/
WORKDIR /usr/src/phoneApp
EXPOSE 8080
LABEL maintainer="dbiosag@.com"
CMD ["java", "-jar", "phoneApp-0.0.1-SNAPSHOT.jar"]