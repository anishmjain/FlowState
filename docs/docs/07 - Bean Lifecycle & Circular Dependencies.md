# FlowState Documentation

# Chapter 7 - Bean Lifecycle & Circular Dependencies

**Sprint:** 3

---

# Objective

Understand when Spring creates Beans, how dependencies determine creation order, and why Circular Dependencies occur.

---

# Bean Lifecycle

A Spring Bean goes through several stages during the application's lifetime.

```
Application Starts

↓

ApplicationContext Created

↓

Component Scan

↓

Bean Created

↓

Dependency Injection

↓

Bean Ready

↓

Application Running

↓

Application Shutdown

↓

Bean Destroyed
```

The Bean is created before the application begins serving requests.

---

# Eager Initialization

By default, Singleton Beans are created during application startup.

Example:

```java
@Service
public class TaskService {

}
```

Spring does **not** wait until:

```java
taskService.getTask();
```

Instead, the Bean is created as soon as Spring initializes the ApplicationContext.

This process is called **Eager Initialization**.

---

# Why?

Imagine 500 users sending requests simultaneously.

If Spring waited until the first request to create every Bean, users would experience unnecessary delays.

Creating Beans during startup ensures the application is ready before accepting traffic.

---

# Dependency Graph

Spring analyzes dependencies between Beans.

Example:

```
TaskController

↓

TaskService

↓

EmailService
```

Spring understands that:

- TaskController requires TaskService.
- TaskService requires EmailService.
- EmailService requires nothing.

Therefore the creation order becomes:

```
EmailService

↓

TaskService

↓

TaskController
```

Spring calculates this automatically.

---

# Constructor Dependency Chain

Example:

```java
@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

}
```

```java
@Service
public class TaskService {

    private final EmailService emailService;

    public TaskService(EmailService emailService) {
        this.emailService = emailService;
    }

}
```

```java
@Service
public class EmailService {

}
```

Startup succeeds because Spring can resolve every dependency.

---

# Circular Dependency

A Circular Dependency occurs when two or more Beans depend on each other.

Example:

```
TaskService

↓

EmailService

↑

└──────────────
```

TaskService requires EmailService.

EmailService requires TaskService.

Neither Bean can be created first.

Spring cannot resolve this dependency graph.

---

# Example

```java
@Service
public class TaskService {

    private final EmailService emailService;

    public TaskService(EmailService emailService) {
        this.emailService = emailService;
    }

}
```

```java
@Service
public class EmailService {

    private final TaskService taskService;

    public EmailService(TaskService taskService) {
        this.taskService = taskService;
    }

}
```

Application startup fails.

---

# Typical Error

```
APPLICATION FAILED TO START

The dependencies of some of the beans in the application context form a cycle.
```

Spring even visualizes the dependency graph:

```
TaskController

↓

TaskService

↑     ↓

EmailService

└─────┘
```

This visualization helps identify the problematic dependency immediately.

---

# Why Circular Dependencies are Bad

Circular dependencies usually indicate poor software design.

They create:

- Tight coupling
- Difficult testing
- Confusing architecture
- Hidden responsibilities

Instead of solving the business problem, classes become dependent on each other's implementation.

---

# Engineering Notes

A Service should not depend on another Service unless there is a genuine business requirement.

If two Services continuously depend on each other, reconsider the architecture.

Very often, introducing another Service or moving shared logic into a separate class removes the cycle.

---

# Common Mistakes

❌ Two Services depending on each other.

❌ Assuming Spring creates Beans in source-code order.

❌ Believing Beans are created when first used.

❌ Solving Circular Dependencies by enabling:

```properties
spring.main.allow-circular-references=true
```

instead of fixing the design.

---

# Interview Notes

### What is Bean Lifecycle?

The sequence of creation, dependency injection, usage and destruction of a Spring Bean.

---

### What is Eager Initialization?

Singleton Beans are created during application startup rather than when first requested.

---

### What is a Circular Dependency?

A situation where two or more Beans depend on each other, preventing Spring from determining a valid creation order.

---

### How should Circular Dependencies be resolved?

By redesigning the application architecture rather than enabling circular references.

---

# FlowState Intuition 💡

Imagine Spring is assembling a project team.

TaskController says:

> "I need TaskService."

TaskService says:

> "I need EmailService."

EmailService says:

> "I'm ready."

Spring hires:

```
EmailService

↓

TaskService

↓

TaskController
```

Now imagine EmailService says:

> "Actually... I also need TaskService."

Spring replies:

> "Who's supposed to start first?"

Nobody has an answer.

Application startup fails.

---

# Real Debugging Case

## Problem

Application failed during startup.

## Root Cause

TaskService depended on EmailService.

EmailService depended on TaskService.

Spring detected a Circular Dependency and stopped Bean creation.

## Resolution

Remove the dependency cycle by redesigning responsibilities.

Never rely on:

```
spring.main.allow-circular-references=true
```

unless there is a compelling legacy reason.

---

# Lessons Learned

- Singleton Beans are created during application startup.
- Spring builds a dependency graph before creating Beans.
- Dependencies determine Bean creation order.
- Circular Dependencies prevent the ApplicationContext from starting.
- Good architecture naturally avoids dependency cycles.

---

# Anish's Notes

(To be filled during development.)