# Simple Book Management Microservice

<table> <tr> <td align="center"><b>Step</b></td> <td align="center"><b>Perintah / Link</b></td> </tr> <tr> <td>Clone Repository</td> <td><code>git clone &lt;repo-url&gt;</code></td> </tr> <tr> <td>Jalankan Aplikasi</td> <td><code>mvn spring-boot:run</code></td> </tr> <tr> <td>Swagger UI</td> <td><a href="http://localhost:8081/swagger-ui/index.html">http://localhost:808a/swagger-ui/index.html</a></td> </tr> <tr> <td>Swagger UI</td> <td><a href="[https://www.postman.com/cryosat-technologist-82314506/workspace/my-workspace/collection/29178897-3659d1f4-a5a2-4cba-a41d-d545ea9fb370?action=share&creator=29178897]">[https://www.postman.com/cryosat-technologist-82314506/workspace/my-workspace/collection/29178897-3659d1f4-a5a2-4cba-a41d-d545ea9fb370?action=share&creator=29178897]</a></td> </tr> </table>
How to Run the Project

1️⃣ Prerequisites

Make sure you have installed:
Java JDK 17.0.17 or newer
Maven 3.9.11
PostgreSQL
Git (optional)
Postman
Swagger

2️⃣ Clone the Repository
git clone <repository-url>

3️⃣ Configure Database
CREATE DATABASE book_db;

4️⃣ Configure Application
server:
port: 8081

spring:
application:
name: book-service

datasource:
url: jdbc:postgresql://localhost:5432/book_db
username: postgres
password: password
driver-class-name: org.postgresql.Driver

jpa:
hibernate:
ddl-auto: update
show-sql: true
properties:
hibernate:
format_sql: true
dialect: org.hibernate.dialect.PostgreSQLDialect

5️⃣ Run the Application
mvn spring-boot:run

postman

Environment Variables
DB_URL=jdbc:postgresql://localhost:5432/book_db
DB_USERNAME=postgres
DB_PASSWORD=password

API Endpoints
/api/books
Method Endpoint Description
POST /api/books Create new book
GET /api/books Get all books
GET /api/books/{id} Get book by ID
PUT /api/books/{id} Update all book data
PATCH /api/books/{id} Update partial book data
DELETE /api/books/{id} Delete book

Swagger
http://localhost:8081/swagger-ui/index.html

Sample Postman Requests
Create Book
{
"title": "Clean Code",
"author": "Robert C Martin",
"isbn": "9780132350884",
"publishedDate": "2008-08-01"
}
Response:
{
"code": 201,
"message": "Book created successfully",
"data": {
"id": 1,
"title": "Clean Code",
"author": "Robert C Martin",
"isbn": "9780132350884",
"publishedDate": "2008-08-01"
}
}
Get Book by ID
GET /api/books/1
{
"code": 200,
"message": "Book retrieved successfully",
"data": {
"id": 1,
"title": "Clean Code",
"author": "Robert C Martin",
"isbn": "9780132350884",
"publishedDate": "2008-08-01"
}
}
Partial Update (PATCH)
PATCH /api/books/1
{
"author": "Uncle Bob"
}
Delete Book
DELETE /api/books/1
{
"code": 200,
"message": "Book deleted successfully",
"data": null
}

Postman Collection

You can import the Postman collection by:

Open Postman

Click Import

Select the provided collection JSON (if included)

Set environment variable:
{{[https://www.postman.com/cryosat-technologist-82314506/workspace/my-workspace/collection/29178897-3659d1f4-a5a2-4cba-a41d-d545ea9fb370?action=share&creator=29178897]}}

Database Design (ER Diagram)
+----------------------+
| BOOKS |
+----------------------+
| PK id : BIGINT |
| title : VARCHAR |
| author : VARCHAR |
| isbn : VARCHAR (UQ) |
| published_date : DATE|
+----------------------+

Validation & Business Rules

All fields are required for CREATE & PUT

ISBN must be unique

ISBN must contain digits only

Author name cannot contain numbers

Date format must be yyyy-MM-dd

Tech Stack

Java 11

Spring Boot

Spring Data JPA

PostgreSQL

Maven

Swagger OpenAPI

Author

Reza Putra
Backend Developer – Java Spring Boot

Notes for Reviewer

Consistent API response format (code, message, data)

Proper usage of PUT vs PATCH

Global exception handling

Clean architecture (Controller – Service – Repository)
