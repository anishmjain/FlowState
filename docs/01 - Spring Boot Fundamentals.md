# FlowState Documentation

# Chapter 1 - Spring Boot Fundamentals

**Date:** 30 June 2026

---

# Sprint 1

## Objective

- Understand the Spring Boot project structure.
- Understand the application entry point.
- Learn what happens when a Spring Boot application starts.
- Understand the purpose of @SpringBootApplication.

---

# Project Structure

```
FlowState
│
├── src
│   ├── main
│   │   ├── java
│   │   └── resources
│   │
│   └── test
│
├── docs
├── pom.xml
├── mvnw
└── README.md
```

---

# Important Files

## pom.xml

The heart of the project.

Responsibilities:

- Stores project dependencies.
- Defines Java version.
- Defines Spring Boot version.
- Controls the build process through Maven.

---

## src/main/java

Contains all Java source code.

Examples:

- Controllers
- Services
- Models
- Repositories
- Configuration

---

## src/main/resources

Contains configuration and application resources.

Initially:

```
application.properties
```

Later it may contain:

- templates
- static
- application-dev.properties
- application-prod.properties

---

## target

Generated during compilation.

Contains compiled classes and generated artifacts.

Never modify manually.

---

# FlowStateApplication.java

This is the application's entry point.

```java
@SpringBootApplication
public class FlowStateApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowStateApplication.class, args);
    }

}
```

---

# @SpringBootApplication

Marks the main Spring Boot application.

Responsibilities:

- Marks the starting point of the application.
- Enables Auto Configuration.
- Enables Component Scanning.
- Enables Spring Configuration.

Spring begins scanning from the package containing this class and all of its sub-packages.

Example:

```
com.anish.flowstate
│
├── controller
├── service
├── repository
└── model
```

Controllers or services placed outside this hierarchy will not be detected automatically.

---

# SpringApplication.run()

Responsibilities:

- Creates the Spring Application Context.
- Creates Spring Beans.
- Reads application.properties.
- Starts the embedded Tomcat server.
- Makes the application ready to receive HTTP requests.

The embedded Tomcat server is only one part of what happens. Most of the work involves creating and configuring the Spring application.

---

# New Terminology

## Spring Container

The Spring Container manages objects called **Beans**.

Instead of creating objects manually:

```java
TaskService service = new TaskService();
```

Spring creates and manages them automatically.

Later, dependency injection will allow Spring to provide these objects wherever they are needed.

---

## Component Scanning

Spring searches the package containing `FlowStateApplication` and all sub-packages for components such as:

- @RestController
- @Controller
- @Service
- @Repository
- @Component

These are automatically registered as Spring Beans.

---

# Common Mistakes

- Controller outside the application's package hierarchy.
- Incorrect package declaration.
- Missing @RestController annotation.
- Assuming a 404 means the application failed to start.

---

# Lessons Learned

- Spring Boot applications contain an embedded Tomcat server.
- A successful application startup does not guarantee that endpoints exist.
- A 404 Whitelabel page usually means no matching endpoint was found.
- Package hierarchy is important because Spring scans from the main application package.

---

# Interview Notes

### What is Spring Boot?

Spring Boot is an opinionated framework built on top of Spring that simplifies the development of production-ready applications by providing auto-configuration and embedded servers.

---

### What does @SpringBootApplication do?

It marks the main Spring Boot application, enables auto-configuration, component scanning, and Spring configuration.

---

### What does SpringApplication.run() do?

It bootstraps the Spring application by creating the Application Context, loading Beans, reading configuration files, and starting the embedded web server.

---

# Sprint Summary

## Completed

- Created the FlowState project.
- Understood the generated project structure.
- Learned the purpose of pom.xml.
- Learned how Spring Boot starts an application.
- Understood the responsibilities of @SpringBootApplication.
- Learned what SpringApplication.run() does.
- Introduced the concept of the Spring Container.

---

## Next Sprint

- Create the Task model.
- Build the first REST Controller.
- Return JSON from the first endpoint.
- Learn @RestController.
- Learn @GetMapping.