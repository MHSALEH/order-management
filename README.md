# order-management
![UML](https://github.com/MHSALEH/order-management/assets/111057336/16d01d76-4031-4a00-8947-0b451940552f)
Order Management System

This storage system hosts a Spring Boot program designed to handle orders based on a given database model.
The project comes with a Docker setup for easy installation and operation.
Its API endpoints are secured, and you can find a Swagger documentation provided.

Initiation Steps

Follow these guidelines to have a copy of the program for development and testing purposes on your local computer.

- Requirements

- Java 17

- Maven

- Docker
  
Build the application

   -  mvn clean
   -  mvn install -DskipTests
     
Build the docker image
   -  docker build -t order_management .
     
run the docker image

- docker run -p 8080:8080 -e SPRING_DATASOURCE_URL="jdbc:mysql://host.docker.internal:3306/order-management? 
useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&zeroDateTimeBehavior=convertToNull" -e SPRING_DATASOURCE_USERNAME=<your_username> -e SPRING_DATASOURCE_PASSWORD=<your_password> my-spring-app
  or you can use docker-compose.yml, which contain both mysql image and application image, then run "docker-compose up", which run the mysql in docker

Repository Duplication

- Run these commands to copy the repository and move into the directory:

git clone https://github.com/MHSALEH/order-management.git

Postman Collection

The Postman collection for API testing is available in the repository.
Download and import it into your Postman software for local testing.

API Description

When the program is running, you can access the API documentation at http://localhost:8080/swagger-ui.html.
This documentation covers all endpoints, models, and authentication details.

Security

JWT is used to secure the APIs in this program.
To acquire the token, use the /api/authenticate endpoint with the appropriate username and password.
Include this token with the "Bearer " prefix in the Authorization header for all requests.

Code Documentation

The code adheres to Java's best practices, which means each method, class, and module is documented with comments and functionality descriptions.
