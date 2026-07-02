# FlowState Documentation

# Chapter 8 - JPA, Hibernate & Spring Data JPA

**Sprint:** 4

---

# Objective

Understand the relationship between JPA, Hibernate, Spring Data JPA, and MySQL before building the persistence layer.

---

# The Problem

Suppose we want to save a Task into MySQL.

Without any framework we would have to:

- Open a database connection
- Write SQL queries
- Execute the SQL
- Read the ResultSet
- Convert rows into Java objects
- Handle exceptions
- Close connections

This results in repetitive boilerplate code.

Example:

```java
Connection connection = DriverManager.getConnection(...);

PreparedStatement statement = connection.prepareStatement(...);

ResultSet result = statement.executeQuery();
```

Writing this for every CRUD operation becomes time-consuming and error-prone.

---

# What is Persistence?

Persistence means that data survives after the application stops running.

Example:

Without persistence:

```
Create Task

↓

Stop Application

↓

Task Lost
```

With persistence:

```
Create Task

↓

Save to Database

↓

Stop Application

↓

Restart Application

↓

Task Still Exists
```

Persistence is one of the primary reasons backend applications use databases.

---

# What is JPA?

JPA stands for **Jakarta Persistence API**.

It is **not** a framework.

It is a **specification (contract)** that defines how Java applications should interact with relational databases.

JPA defines:

- Entity Mapping
- Relationships
- Persistence Rules
- CRUD Contracts

JPA does **not** execute SQL.

---

# What is Hibernate?

Hibernate is an **ORM Framework**.

ORM stands for:

**Object Relational Mapping**

Hibernate implements the JPA specification.

Responsibilities:

- Generate SQL
- Execute SQL
- Convert Java Objects into Database Rows
- Convert Database Rows into Java Objects
- Manage Entity Lifecycle

Hibernate is the component that actually communicates with the database.

---

# What is Spring Data JPA?

Spring Data JPA sits on top of Hibernate.

It reduces the amount of code developers need to write.

Instead of writing:

```java
Connection

PreparedStatement

ResultSet
```

Developers simply write:

```java
repository.save(task);
```

Spring Data JPA delegates the work to Hibernate.

Hibernate generates the SQL.

---

# Relationship Between Technologies

```
Application

↓

Spring Data JPA

↓

Hibernate

↓

JPA Specification

↓

JDBC Driver

↓

MySQL
```

Each layer builds on top of the previous one.

---

# Responsibilities

## JPA

- Defines rules
- Defines annotations
- Defines persistence contracts

---

## Hibernate

- Implements JPA
- Generates SQL
- Executes SQL
- Maps Java Objects to Database Tables

---

## Spring Data JPA

- Simplifies repository creation
- Eliminates boilerplate CRUD code
- Integrates Hibernate with Spring Boot

---

# Why not use JDBC directly?

JDBC is still used internally.

However, developers would need to manually:

- Open Connections
- Close Connections
- Write SQL
- Map ResultSets

This creates repetitive code.

Spring Data JPA and Hibernate automate these responsibilities.

---

# Mental Model

Imagine driving a car.

```
Traffic Rules

↓

Driver

↓

Car
```

Spring Version:

```
JPA

↓

Hibernate

↓

MySQL
```

JPA defines the rules.

Hibernate follows those rules.

MySQL stores the data.

---

# Flow Architecture

```
Controller

↓

Service

↓

Repository

↓

Spring Data JPA

↓

Hibernate

↓

JDBC Driver

↓

MySQL
```

Every request that accesses the database eventually follows this path.

---

# Engineering Notes

JPA is only a specification.

Hibernate is one implementation of JPA.

Spring Data JPA is a Spring abstraction built on top of Hibernate.

Although developers frequently say "using JPA", most Spring Boot applications are actually using:

Spring Data JPA + Hibernate + MySQL.

---

# Common Mistakes

❌ Thinking JPA is a framework.

❌ Thinking Hibernate and JPA are the same technology.

❌ Writing SQL inside the Service layer.

❌ Believing Spring Data JPA replaces Hibernate.

❌ Assuming Spring Data JPA communicates directly with MySQL.

---

# Interview Notes

### What is JPA?

A specification that defines how Java applications should interact with relational databases.

---

### Is JPA a framework?

No.

It is a specification.

---

### Who implements JPA?

Hibernate (among other providers).

---

### What is Hibernate?

An ORM framework that implements JPA and generates SQL.

---

### What is Spring Data JPA?

A Spring abstraction that simplifies database access by building on top of Hibernate.

---

### Which component actually talks to MySQL?

Hibernate.

---

# FlowState Intuition 💡

Imagine three people working together.

JPA says:

> "These are the rules."

Hibernate says:

> "I'll do the work."

Spring Data JPA says:

> "I'll make your life easier."

The developer only writes Java code.

The frameworks handle the database communication.

---

# Lessons Learned

- Persistence means data survives application restarts.
- JPA defines the rules.
- Hibernate implements those rules.
- Spring Data JPA simplifies database development.
- Hibernate generates and executes SQL.
- Spring Data JPA does not replace Hibernate—it builds on top of it.

---

# Anish's Notes

(To be filled during development.)