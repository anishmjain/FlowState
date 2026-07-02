# FlowState Documentation

# Chapter 6 - Spring Container & Component Scanning

**Sprint:** 3

---

# Objective

Understand how Spring discovers application components, creates Beans, and manages them inside the Spring Container.

---

# What is the Spring Container?

The Spring Container is responsible for creating, managing, and providing all Spring Beans.

The implementation commonly used in Spring Boot applications is the **ApplicationContext**.

Think of it as the central manager of the application.

Responsibilities:

- Create Beans
- Store Beans
- Inject Beans
- Manage Bean lifecycle
- Resolve dependencies

---

# What is ApplicationContext?

The ApplicationContext is Spring's IoC (Inversion of Control) Container.

Every Bean inside the application is managed through it.

When the application starts:

```
main()

↓

SpringApplication.run()

↓

ApplicationContext Created

↓

Component Scan Begins

↓

Beans Created

↓

Dependencies Injected

↓

Application Ready
```

Without the ApplicationContext, Dependency Injection would not exist.

---

# Component Scanning

Spring automatically searches the application's packages for classes annotated with Spring stereotypes.

Example:

```
com.anish.flowstate

├── controller
├── service
├── repository
├── config
└── model
```

Spring scans every package below the package containing:

```java
@SpringBootApplication
```

---

# What does Spring look for?

During Component Scanning Spring searches for:

```java
@Component

@Service

@Repository

@Controller

@RestController
```

Whenever one of these annotations is found, Spring creates a Bean.

---

# Why was TaskService discovered?

TaskService satisfied three conditions:

- Located inside the component scan package.
- Annotated with `@Service`.
- `@Service` is a specialization of `@Component`.

Therefore Spring created a TaskService Bean automatically.

---

# What is @Component?

`@Component` is the base stereotype annotation.

The following annotations are specialized versions of `@Component`:

```
@Component

├── @Service

├── @Repository

├── @Controller

└── @RestController
```

Each specialization gives additional semantic meaning while still being discovered during Component Scanning.

---

# Startup Sequence

```
Application Starts

↓

SpringApplication.run()

↓

ApplicationContext Created

↓

Component Scan

↓

TaskService Found

↓

TaskController Found

↓

Beans Created

↓

Dependencies Injected

↓

Tomcat Starts

↓

Application Ready
```

---

# Package Structure Matters

Spring scans downward from the package containing:

```java
@SpringBootApplication
```

Example:

```
com.anish.flowstate
```

Automatically scanned:

```
com.anish.flowstate.controller

com.anish.flowstate.service

com.anish.flowstate.repository
```

Not automatically scanned:

```
com.other.email

com.random.test
```

These packages require explicit configuration using `@ComponentScan`.

---

# Engineering Notes

Component Scanning removes the need to manually register every Bean.

Instead of explicitly creating objects, developers describe application components using annotations.

Spring builds the application automatically.

---

# Common Mistakes

❌ Placing classes outside the component scan package.

❌ Forgetting stereotype annotations.

❌ Assuming every Java object becomes a Bean.

❌ Confusing ApplicationContext with Dependency Injection.

---

# Interview Notes

### What is the Spring Container?

The Spring Container is responsible for creating, managing and injecting Spring Beans.

---

### What is ApplicationContext?

ApplicationContext is Spring's implementation of the IoC Container.

---

### What is Component Scanning?

The process where Spring searches packages for annotated classes and automatically creates Beans.

---

### Difference between @Component and @Service?

`@Component` is the generic stereotype.

`@Service` is a specialization indicating that the class contains business logic.

Both become Spring Beans.

---

# FlowState Intuition 💡

Imagine Spring is a company.

ApplicationContext is the CEO.

Component Scan is HR.

Every class wearing a Spring annotation gets hired as an employee (Bean).

When one employee says,

> "I need a TaskService."

the CEO replies,

> "Already hired one. Go work together."

No employee hires another employee directly.

---

# Lessons Learned

- ApplicationContext is the heart of every Spring Boot application.
- Component Scanning discovers annotated classes automatically.
- Spring creates Beans during application startup.
- Only classes inside the component scan package are automatically discovered.
- `@Service`, `@Repository`, `@Controller`, and `@RestController` are all specialized forms of `@Component`.

---

# Anish's Notes

(To be filled during development.)