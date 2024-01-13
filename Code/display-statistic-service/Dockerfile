FROM openjdk:17-oracle
EXPOSE 8080
ADD target/display-statistic-service.jar display-statistic-service.jar
ENTRYPOINT ["sh", "-c", "java -jar /display-statistic-service.jar"]
