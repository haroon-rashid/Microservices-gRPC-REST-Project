FROM openjdk:17-oracle
EXPOSE 8080
ADD target/file-reader-service.jar file-reader-service.jar
ENTRYPOINT ["sh", "-c", "java -jar /file-reader-service.jar"]
