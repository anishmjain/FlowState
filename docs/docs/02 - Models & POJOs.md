# FlowState Documentation

# Chapter 2 - Models & POJOs

**Sprint:** 2

---

# Objective

Learn what a Model is and why every Spring Boot application starts by modelling real-world objects.

---

# What is a Model?

A Model represents a real-world entity inside our application.

Example:

Real World

```
Task
```

↓

Java Representation

```java
Task
```

Spring itself does not know what a Task is.

We teach Spring by creating a Java class that represents the object.

---

# Task Model

```java
public class Task {

    private int id;
    private String title;
    private String description;
    private boolean completed;

    // Constructors

    // Getters & Setters

    // toString()

}
```

---

# What is a POJO?

POJO stands for:

> **Plain Old Java Object**

A POJO is simply a normal Java class.

Characteristics:

- No special inheritance
- No Spring annotations
- Stores data
- Contains constructors
- Contains getters and setters

Example:

```java
public class Task {

    private int id;
    private String title;

}
```

---

# Why do we need a Model?

Suppose a client sends:

```json
{
    "id": 1,
    "title": "Learn Spring Boot"
}
```

Spring needs a Java object capable of storing that information.

That object is our Model.

---

# Current Architecture

```
Browser

↓

Controller

↓

Task Object

↓

Browser
```

Later this evolves into:

```
Browser

↓

Controller

↓

Service

↓

Repository

↓

Database
```

---

# Engineering Notes

The Model should represent **data**, not business logic.

Avoid placing validation, database access, or application logic inside the model during this stage of the project.

Later, the same class will evolve into a JPA Entity using:

```java
@Entity
```

without changing its core responsibility.

---

# Common Mistakes

❌ Putting business logic inside the Model.

❌ Creating database code inside the Model.

❌ Thinking Model and Database Table are the same thing.

---

# Interview Notes

### What is a Model?

A Model represents a real-world object inside the application.

---

### What is a POJO?

A Plain Old Java Object used to represent application data without framework-specific requirements.

---

# Lessons Learned

- Every application starts by modelling its data.
- Models represent business entities.
- POJOs are plain Java classes.
- Spring works with Java objects before databases are introduced.

---

# Anish's Notes

```
(To be filled by me during development.)
```