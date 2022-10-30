FROM maven:3.8.5-jdk-11-slim

WORKDIR video-microservice

COPY . .

RUN apt-get -y update
RUN apt-get install -y ffmpeg

ENTRYPOINT mvn -s maven-settings.xml spring-boot:run -Dspring.profiles.active=${ACTIVE_PROFILE}
