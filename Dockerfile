FROM openjdk:latest
COPY target/service-1.0-SNAPSHOT.jar /opt/app/
WORKDIR /opt/app
EXPOSE 8080
CMD java -jar service-1.0-SNAPSHOT.jar