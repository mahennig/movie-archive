FROM openjdk:8-jdk-alpine
ADD target/moviearchive-1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]