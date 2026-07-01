# FlowState Documentation

# Chapter 4 - Service Layer

**Sprint:** 2

---

# Objective

Understand the purpose of the Service Layer and why business logic should not be placed inside Controllers.

---

# What is a Service?

A Service contains the application's business logic.

It sits between the Controller and the data layer.

The Controller asks **what** needs to happen.

The Service knows **how** it should happen.

---

# Responsibilities

- Business rules
- Data processing
- Validation
- Calculations
- Calling repositories
- Returning results

A Service should not know anything about HTTP requests or URLs.

---

# Why do we need a Service?

Without a Service:

```
Controller

↓

Validation

↓

Business Rules

↓

Database

↓

Response
```

Controllers quickly become difficult to maintain.

With a Service:

```
Controller

↓

Service

↓

Repository

↓

Database
```

Each layer has a single responsibility.

---

# Current Implementation

```java
public class TaskService {

    public Task getTask() {

        return new Task(
                1,
                "Learn Spring Boot",
                "Complete Sprint 2",
                false
        );

    }

}
```

The Service currently returns hardcoded data.

This is intentional.

Later this method will retrieve data from a Repository instead.

---

# Request Flow

```
Browser

↓

TaskController

↓

TaskService

↓

Task

↓

Jackson

↓

JSON
```

---

# Engineering Notes

The Service Layer exists to separate business logic from HTTP handling.

Controllers should coordinate requests and responses.

Services should contain application rules.

Keeping responsibilities separate makes the application easier to test, extend and maintain.

---

# Common Mistakes

❌ Writing business logic inside Controllers.

❌ Accessing the database directly from Controllers.

❌ Mixing validation with HTTP request handling.

---

# Interview Notes

### What is the Service Layer?

The Service Layer contains the application's business logic.

---

### Why not put everything inside Controllers?

Because Controllers should only coordinate HTTP communication.

Separating business logic improves readability, maintainability and testing.

---

# Lessons Learned

- Services contain business logic.
- Controllers should remain lightweight.
- The Service Layer sits between the Controller and the Repository.
- Today's Service returns hardcoded data, but tomorrow it will communicate with the database.

---

# Anish's Notes

(To be filled during development.)