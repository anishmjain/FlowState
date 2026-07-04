# FlowState - Project Charter

## Project Objective

Build a production-quality Spring Boot backend while understanding every architectural decision behind it.

The objective is not simply to complete a CRUD application, but to develop the ability to explain:

- Why every layer exists.
- Why each design decision was made.
- How Spring Boot components interact.
- How similar systems are engineered in production.

---

# Target Stack

### Core
- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- MySQL

### API
- REST
- DTO Pattern
- Validation
- Exception Handling
- Logging
- Swagger / OpenAPI

### Security
- Spring Security
- JWT Authentication
- Authorization

### Engineering
- Git & GitHub
- Clean Architecture
- SOLID Principles
- Design Patterns

---

# Engineering Workflow

Every topic follows the same lifecycle.

```
Problem
    ↓
Requirements
    ↓
Architecture
    ↓
Design Decisions
    ↓
Implementation
    ↓
Code Review
    ↓
Refactoring
    ↓
Commit
    ↓
Documentation
    ↓
Next Sprint
```

No implementation begins before understanding the architecture.

---

# Engineering Rules

## Before Writing Code

Every new concept must answer:

1. What problem does it solve?
2. Why was it designed this way?
3. Who creates it?
4. Who calls it?
5. Who owns its lifecycle?
6. What alternatives exist?

Framework features should never be treated as "magic."

---

## Coding Standards

- Constructor Injection only.
- Thin Controllers.
- Business logic belongs in Services.
- Persistence belongs in Repositories.
- DTOs are the external contract.
- Entities remain internal.
- Mapping stays isolated.
- Global exception handling.
- Validation at API boundaries.
- Clear package organization.

---

# Review Standard

Every implementation is reviewed as if it were a production Pull Request.

Possible outcomes:

- APPROVED
- APPROVED WITH CHANGES
- REQUEST CHANGES
- REJECTED

Reviews focus on:

- Architecture
- Separation of Concerns
- Spring Best Practices
- Maintainability
- Scalability
- Clean Code

Incorrect reasoning is corrected directly with supporting explanation.

---

# Session Structure

Every coding session begins with:

```
Sprint Number

Objectives

Dependencies

Expected Deliverables

Expected Commit
```

Every session ends with:

```
Completed

Remaining Work

Technical Debt

Commit Created

Next Sprint
```

---

# Documentation

Maintain the following project documents.

```
docs/

00 - Project Charter.md
01 - Architecture Decision Log.md
02 - Sprint Log.md
03 - Learning Notes.md
```

---

# Current Progress

## Completed

- REST CRUD API
- MVC Architecture
- Spring Data JPA
- Hibernate
- DTO Pattern
- Manual Mapper
- Validation
- Global Exception Handling
- Logging
- Swagger / OpenAPI
- Spring Security Foundation
- User Entity
- UserRepository
- CustomUserDetailsService

## Upcoming

- AuthenticationManager
- BCrypt Password Encoder
- Authentication Controller
- JWT Generation
- JWT Validation Filter
- Role-based Authorization
- Refresh Tokens
- Deployment

---

# Working Agreement

The project is conducted as an engineering collaboration rather than a tutorial.

Communication should remain:

- Direct
- Technical
- Structured
- Concise

Feedback should prioritize correctness over encouragement.

The focus is understanding systems, architecture, and engineering trade-offs rather than memorizing framework syntax.