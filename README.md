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
- **H2 Database** (or your choice of database)
- **JUnit 5** for testing

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Geogsoc/springboot-demo-round-2


## API Endpoints

| Method  | Endpoint               | Description                  |
|---------|------------------------|------------------------------|
| POST    | `/api/v1/person`        | Add a new person             |
| GET     | `/api/v1/person`        | Get all people               |
| GET     | `/api/v1/person/{id}`   | Get person by ID             |
| DELETE  | `/api/v1/person/{id}`   | Delete a person by ID        |
| PUT     | `/api/v1/person/{id}`   | Update an existing person by ID |

### Note:
When running locally, the base URL is typically `http://localhost:8080`. So, for example:
- To add a new person: `POST http://localhost:8080/api/v1/person`
- To get all people: `GET http://localhost:8080/api/v1/person`