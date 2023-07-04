From openjdk:17
EXPOSE 8080
ADD target/order-management-0.0.1-SNAPSHOT.jar order-management.jar
ENTRYPOINT ["java","-jar","order-management.jar"]