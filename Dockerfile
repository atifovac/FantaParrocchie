FROM maven:3.5-jdk-8-alpine
MAINTAINER Daniele Salvatore <danl.salvtr@gmail.com>
EXPOSE 8080
ADD /pom.xml pom.xml
ADD /src src
#ENTRYPOINT ["java", "-jar", "fantacalcio-0.1-SNAPSHOT.jar"]
CMD ["mvn", "clean", "install", "exec:java"]
