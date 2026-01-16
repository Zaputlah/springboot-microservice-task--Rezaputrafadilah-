Simple Book Management Microservice
<table> <tr> <td align="center"><b>Step</b></td> <td align="center"><b>Perintah / Link</b></td> </tr> <tr> <td>Clone Repository</td> <td><code>git clone &lt;repo-url&gt;</code></td> </tr> <tr> <td>Jalankan Aplikasi</td> <td><code>mvn spring-boot:run</code></td> </tr> <tr> <td>Swagger UI</td> <td><a href="http://localhost:8081/swagger-ui/index.html">http://localhost:8081/swagger-ui/index.html</a></td> </tr> <tr> <td>Postman Collection</td> <td><a href="https://www.postman.com/cryosat-technologist-82314506/workspace/my-workspace/collection/29178897-3659d1f4-a5a2-4cba-a41d-d545ea9fb370?action=share&creator=29178897">Postman Collection Link</a></td> </tr> </table>

## 🧱 Design Decisions
- Single Entity Design: Only Book entity for simplicity
- Database Choice: PostgreSQL for production-ready persistence
- API Response Format: Consistent {code, message, data} structure
- Validation Layer: Built-in and custom validations for business rules

📌 Prerequisites
- ✅ Java JDK 17.0.17 or newer
- ✅ Maven 3.9.11
- ✅ PostgreSQL
- ✅ Git (optional)
- ✅ Postman
- ✅ Swagger

## 🗄️ Database Configuration
# sql
- Create database
CREATE DATABASE book_db;
## ⚙️ Application Configuration
yaml
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
## 🚀 Run the Application
# bash
- mvn spring-boot:run
# 🔧 Postman Environment Variables
- text
- DB_URL=jdbc:postgresql://localhost:5432/book_db
- DB_USERNAME=postgres
- DB_PASSWORD=password
## 📚 API Documentation
## 📖 BOOK MANAGEMENT
<table> <tr> <th>Method</th> <th>Endpoint</th> <th>Description</th> <th>Status Codes</th> </tr> <tr> <td><b>POST</b></td> <td><code>/api/books</code></td> <td>Create new book</td> <td>201, 400, 409</td> </tr> <tr> <td><b>GET</b></td> <td><code>/api/books</code></td> <td>Get all books</td> <td>200, 404</td> </tr> <tr> <td><b>GET</b></td> <td><code>/api/books/{id}</code></td> <td>Get book by ID</td> <td>200, 404</td> </tr> <tr> <td><b>PUT</b></td> <td><code>/api/books/{id}</code></td> <td>Update all book data</td> <td>200, 400, 404</td> </tr> <tr> <td><b>PATCH</b></td> <td><code>/api/books/{id}</code></td> <td>Update partial book data</td> <td>200, 400, 404</td> </tr> <tr> <td><b>DELETE</b></td> <td><code>/api/books/{id}</code></td> <td>Delete book</td> <td>200, 404</td> </tr> </table>

## 🏗️ Database Design
## 📊 ER Diagram
# text
```sql
+----------------------+
|        BOOKS         |
+----------------------+
| PK id : BIGINT       |
| title : VARCHAR      |
| author : VARCHAR     |
| isbn : VARCHAR (UQ)  |
| published_date : DATE|
+----------------------+
```
## ⚖️ Validation & Business Rules
- ✅ All fields are required for CREATE & PUT operations
- ✅ ISBN must be unique across all books
- ✅ ISBN must contain digits only (numeric validation)
- ✅ Author name cannot contain numbers (alphabetical validation)
- ✅ Date format must be yyyy-MM-dd (ISO date format)

## 🛠️ Tech Stack
<table> <tr> <td><b>Backend</b></td> <td>Java 11, Spring Boot, Spring Data JPA</td> </tr> <tr> <td><b>Database</b></td> <td>PostgreSQL</td> </tr> <tr> <td><b>Build Tool</b></td> <td>Maven</td> </tr> <tr> <td><b>Documentation</b></td> <td>Swagger OpenAPI</td> </tr> <tr> <td><b>Testing</b></td> <td>Postman, Swagger UI</td> </tr> </table>

## 📋 Sample API Requests & Responses
# ➕ POST /api/books - Create Book
# Request:
```json
{
  "title": "Clean Code",
  "author": "Robert C Martin",
  "isbn": "9780132350884",
  "publishedDate": "2008-08-01"
}
```
# Response (201 Created):
```json
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
```
## 🔍 GET /api/books/1 - Get Book by ID
# Response (200 OK):
```json
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
```
## 🔄 PATCH /api/books/1 - Partial Update
# Request:
```json
{
  "author": "Uncle Bob"
}
```
## 🗑️ DELETE /api/books/1 - Delete Book
# Response (200 OK):
```json
{
  "code": 200,
  "message": "Book deleted successfully",
  "data": null
}
```

## 📊 Test Results Gallery

### Postman Results

<div align="center">
  <table>
    <tr>
      <td colspan="3" align="center"><b>CREATE Operations</b></td>
    </tr>
    <tr>
      <td><img src="doc/postman/Response_BerhasilCreate.png" width="180" /></td>
      <td><img src="doc/postman/Response_GagalAuthor.png" width="180" /></td>
      <td><img src="doc/postman/Response_GagalDate.png" width="180" /></td>
    </tr>
    <tr>
      <td>Success</td>
      <td>Invalid Author</td>
      <td>Invalid Date</td>
    </tr>
  </table>
  
  <table>
    <tr>
      <td colspan="2" align="center"><b>READ Operations</b></td>
    </tr>
    <tr>
      <td><img src="doc/postman/Response_AllBook.png" width="250" /></td>
      <td><img src="doc/postman/Response_BerhasilBookById.png" width="250" /></td>
    </tr>
    <tr>
      <td>Get All Books</td>
      <td>Get by ID</td>
    </tr>
  </table>
</div>

### Swagger UI Results

<div align="center">
  <table>
    <tr>
      <td><img src="doc/swagger/Response_AllBook.png" width="200" /></td>
      <td><img src="doc/swagger/Response_CreateBook.png" width="200" /></td>
      <td><img src="doc/swagger/Response_UpdateByField.png" width="200" /></td>
    </tr>
    <tr>
      <td>GET All</td>
      <td>POST Create</td>
      <td>PATCH Update</td>
    </tr>
  </table>
</div>

### 🧪 Postman Collection
- Import Instructions:
- Open Postman
- Click Import
- Select the provided collection JSON (if included)
- Set environment variable:

## 👨‍💻 Author
- Reza Putra fadilah
- Backend Developer – Java Spring Boot

## 📝 Notes for Reviewer
- ✅ Consistent API response format (code, message, data)
- ✅ Proper usage of PUT vs PATCH (full vs partial update)
- ✅ Global exception handling with meaningful error messages
- ✅ Clean architecture (Controller – Service – Repository)
- ✅ Comprehensive validation with business rules enforcement
- ✅ RESTful API design following best practices
- ✅ Proper HTTP status codes for different scenarios
- ✅ Swagger documentation for easy API exploration
- ✅ Postman collection for testing convenience

## 🔗 Useful Links
- Swagger UI: http://localhost:8081/swagger-ui/index.html
- Postman Collection: https://www.postman.com/cryosat-technologist-82314506/workspace/my-workspace/collection/29178897-3659d1f4-a5a2-4cba-a41d-d545ea9fb370?action=share&creator=29178897
- Database: PostgreSQL running on port 5432
- Application: Spring Boot running on port 8081
<div align="center"> <em>🎯 Simple yet powerful Book Management Microservice built with Spring Boot</em> </div>
