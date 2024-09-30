# Spring Boot Demo - Round 2

## Overview
This is a Spring Boot demo project named **springboot_demo_round2**. The project demonstrates the use of a RESTful API for managing students, built using Spring Boot. It includes a `StudentService`, `StudentRepository`, and `StudentController` for the main logic.

## Features
- Create, read, update, and delete student records.
- Use `JpaRepository` for database interactions.
- Implement unit testing with Mockito and JUnit.
- Custom repository method: `findStudentByEmail(String email)`.

## Requirements
- **Java 17**
- **Maven 3.8+**
- **Spring Boot 3.x**
- **PostgreSQL** (or your choice of database)
- **JUnit 5** for testing

# Step 1: Clone the repository
git clone https://github.com/Geogsoc/springboot-demo-round-2

# Step 2: Connect to PostgreSQL as the superuser
psql -U postgres

# Step 3: Create the "student" database
CREATE DATABASE student;

# Step 4: Grant all privileges to your PostgreSQL user (replace 'your_username' with your actual username)
GRANT ALL PRIVILEGES ON DATABASE "student" TO your_username;

# (Optional) Grant privileges to the default 'postgres' user
GRANT ALL PRIVILEGES ON DATABASE "student" TO postgres;

# Step 5: Exit the PostgreSQL CLI
\q


## API Endpoints

| Method  | Endpoint                   | Description                                            |
|---------|----------------------------|--------------------------------------------------------|
| POST    | `/api/v1/student`           | Add a new student                                      |
| GET     | `/api/v1/student`           | Get all students                                       |
| DELETE  | `/api/v1/student/{studentId}`| Delete a student by ID                                 |
| PUT     | `/api/v1/student/{studentId}`| Update an existing student's name and/or email by ID   |


**Example Request:**

### Note:
When running locally, the base URL is typically `http://localhost:8080`. So, for example:
- To add a new person: `POST http://localhost:8080/api/v1/person`
- To get all people: `GET http://localhost:8080/api/v1/person`

### Updating a Student

To update a student, you can use the following `PUT` request. The **name** and **email** are optional query parameters, so you can update one or both fields.


```bash
PUT http://localhost:8080/api/v1/student/{studentId}?name=NewName&email=newemail@example.com

