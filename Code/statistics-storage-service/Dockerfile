FROM openjdk:17-oracle
EXPOSE 8080
ADD target/statistics-storage-service.jar statistics-storage-service.jar
ENTRYPOINT ["sh", "-c", "java -jar /statistics-storage-service.jar"]
