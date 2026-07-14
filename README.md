# 🚀 FlowState

> A secure, production-oriented Task Management REST API built with Spring Boot, Spring Security, JWT Authentication, and MySQL.

FlowState is a backend application that allows users to securely manage their personal tasks. It implements modern backend development practices including stateless authentication, role-based authorization, ownership-based data access, centralized exception handling, DTO mapping, filtering, pagination, and sorting.

This project was built as part of my Spring Boot learning journey with a strong emphasis on clean architecture, security, and REST API design.

---

## Running the Project

### Prerequisites

- Java 17+
- Maven
- MySQL

### Clone the repository

```bash
git clone https://github.com/anishmjain/FlowState.git
```

Navigate to the project:

```bash
cd FlowState
```

### Configure the database

Update your database credentials in:

```properties
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/flowstate
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Build the project

```bash
mvn clean install
```

### Run the application

```bash
mvn spring-boot:run
```

The application will start at:

```
http://localhost:8080
```

### Swagger UI

Once the application is running, open:

```
http://localhost:8080/swagger-ui/index.html
```
---


# Features

## Authentication & Authorization

- JWT-based Authentication
- BCrypt Password Encryption
- Stateless Security
- Spring Security Integration
- Role-Based Authorization (USER / ADMIN)

---

## Task Management

Users can:

- Create Tasks
- Update Tasks
- Delete Tasks
- Mark Tasks as Completed
- View Individual Tasks
- View All Personal Tasks

Every task is securely associated with its owner, ensuring users can only access their own data.

---

## Search & Filtering

Supports:

- Filter by Priority
- Pagination
- Sorting

Example:

```http
GET /tasks?priority=HIGH&page=0&size=5&sortBy=title&direction=desc
```

---

## Admin Module

Administrators can:

- View all users
- Update user roles

Endpoints are protected using Spring Security role-based authorization.

---

## Global Exception Handling

Centralized exception handling using `@RestControllerAdvice`.

Handles:

- Task Not Found
- Duplicate Username
- Validation Errors
- Invalid Query Parameters
- Invalid Enum Values
- Unexpected Server Errors

Returns consistent JSON error responses throughout the application.

---

# Tech Stack

## Backend

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL

## Tools

- Maven
- JWT
- BCrypt
- Swagger / OpenAPI
- IntelliJ IDEA

---

# Project Structure

```
src/main/java

com.anish.flowstate
│
├── config
├── controller
├── dto
├── exception
├── mapper
├── model
├── repository
├── security
└── service
```

---

# Architecture

```
                Client
                   │
                   ▼
             REST Controller
                   │
                   ▼
               Service Layer
                   │
                   ▼
             Repository Layer
                   │
                   ▼
                 MySQL
```

Authentication Flow

```
JWT Token
      │
      ▼
JWT Filter
      │
      ▼
Security Context
      │
      ▼
Authenticated User
      │
      ▼
Business Logic
```

---

# REST API

## Authentication

| Method | Endpoint |
|---------|----------|
| POST | `/auth/register` |
| POST | `/auth/login` |

---

## User

| Method | Endpoint |
|---------|----------|
| GET | `/users/me` |

---

## Tasks

| Method | Endpoint |
|---------|----------|
| GET | `/tasks` |
| GET | `/tasks/{id}` |
| POST | `/tasks` |
| PUT | `/tasks/{id}` |
| DELETE | `/tasks/{id}` |
| PATCH | `/tasks/{id}/complete` |

---

## Admin

| Method | Endpoint |
|---------|----------|
| GET | `/admin/users` |
| PATCH | `/admin/users/{id}/role` |

---

# Query Features

## Pagination

```http
GET /tasks?page=0&size=5
```

---

## Filtering

```http
GET /tasks?priority=HIGH
```

---

## Sorting

```http
GET /tasks?sortBy=title&direction=asc
```

---

## Combined Example

```http
GET /tasks?priority=HIGH&page=0&size=5&sortBy=title&direction=desc
```

---

# Security

FlowState implements multiple security layers.

- JWT Authentication
- Stateless Sessions
- BCrypt Password Hashing
- Role-Based Authorization
- Ownership-Based Data Access
- Protected REST Endpoints

---

# Engineering Decisions

## DTO Pattern

DTOs separate API contracts from persistence models, preventing entities from being directly exposed to clients.

---

## Mapper Layer

Dedicated mapper classes convert between entities and DTOs, keeping controllers and services clean.

---

## Search Criteria Pattern

Filtering, pagination, and sorting are encapsulated inside a `TaskSearchCriteria` object rather than expanding service method signatures.

---

## Layered Architecture

Business logic resides in the service layer while controllers remain responsible only for HTTP request handling.

---

## Global Exception Handling

All exceptions are handled centrally using `@RestControllerAdvice`, ensuring consistent API responses.

---

## Ownership-Based Authorization

Repository methods enforce ownership by querying tasks using both task ID and authenticated user, preventing unauthorized data access.

---

# Validation

Request validation is implemented using Jakarta Bean Validation.

Examples include:

- Empty Title
- Empty Description
- Invalid Priority
- Invalid Role
- Invalid Query Parameters

---

# Future Improvements

- Due Dates
- Keyword Search
- Unit Testing
- Integration Testing
- Docker Support
- CI/CD Pipeline
- Email Notifications

---

# Learning Outcomes

This project helped reinforce practical experience with:

- Spring Boot
- Spring Security
- JWT Authentication
- REST API Design
- Spring Data JPA
- Hibernate
- MySQL
- DTO Mapping
- Layered Architecture
- Exception Handling
- Pagination
- Sorting
- Validation
- Role-Based Authorization

---

# Author

**Anish Manish Jain**

Associate Java Developer

Built as a personal project to strengthen backend engineering skills using Spring Boot and modern Java development practices.