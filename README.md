# order-management
![UML](https://github.com/MHSALEH/order-management/assets/111057336/16d01d76-4031-4a00-8947-0b451940552f)
Order Management System

This storage system hosts a Spring Boot program designed to handle orders based on a given database model.
The project comes with a Docker setup for easy installation and operation.
Its API endpoints are secured, and you can find a Swagger documentation provided.

Initiation Steps

Follow these guidelines to have a copy of the program for development and testing purposes on your local computer.

Requirements

Java 17

Maven

Docker

Postman

Repository Duplication

Run these commands to copy the repository and move into the directory:

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
