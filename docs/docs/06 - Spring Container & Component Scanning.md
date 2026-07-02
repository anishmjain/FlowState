# FlowState Documentation

# Chapter 5 - Dependency Injection & Spring Beans

**Sprint:** 3

---

# Objective

Understand how Spring manages objects, injects dependencies, and removes the need to manually create objects using the `new` keyword.

---

# What is a Bean?

A Bean is an object whose lifecycle is managed by the Spring Container.

Example:

```java
@Service
public class TaskService {

}
```

Spring automatically creates an object of this class and manages it throughout the application's lifetime.

A normal Java object:

```java
Task task = new Task();
```

is **not** a Spring Bean because Spring did not create or manage it.

---

# What is Dependency Injection?

Dependency Injection (DI) is the process where Spring provides the required objects (dependencies) to a class instead of the class creating them itself.

Without DI:

```java
private TaskService taskService = new TaskService();
```

With DI:

```java
private final TaskService taskService;

public TaskController(TaskService taskService) {
    this.taskService = taskService;
}
```

The controller no longer creates the object.

It simply declares what it needs.

---

# What is a Dependency?

A dependency is another object that a class requires to perform its work.

Example:

```
TaskController
      ↓
TaskService
```

`TaskController` depends on `TaskService`.

---

# Constructor Injection

Constructor Injection is the recommended way of injecting dependencies in Spring.

Example:

```java
private final TaskService taskService;

public TaskController(TaskService taskService) {
    this.taskService = taskService;
}
```

Benefits:

- Mandatory dependencies
- Immutable (`final`) fields
- Easier testing
- Clear object requirements

---

# What is Inversion of Control (IoC)?

Before Spring:

```
Controller

↓

Creates TaskService

↓

Uses TaskService
```

After Spring:

```
Spring Container

↓

Creates TaskService

↓

Injects TaskService

↓

Controller Uses TaskService
```

The responsibility of creating objects moves from the application code to Spring.

This inversion of responsibility is called **Inversion of Control (IoC).**

---

# Request Flow

```
Browser

↓

DispatcherServlet

↓

TaskController

↓

TaskService Bean

↓

Task Object

↓

Jackson

↓

JSON

↓

Browser
```

---

# Object Ownership

Without Spring:

```
Controller

↓

new TaskService()
```

The Controller owns the object.

With Spring:

```
Controller

↓

"I need TaskService."

↓

Spring Container

↓

Provides TaskService Bean
```

Spring owns the object lifecycle.

---

# Why is Dependency Injection useful?

Dependency Injection separates **object creation** from **object usage**.

Instead of creating dependencies manually, classes simply declare what they require.

This leads to:

- Lower coupling
- Better maintainability
- Easier testing
- Reusable components
- Cleaner architecture

---

# Engineering Notes

Spring does not replace Java.

Java teaches us how to create objects.

Spring manages those objects after they are created.

Every Spring class should describe **what it needs**, not **how to build what it needs**.

---

# Common Mistakes

❌ Creating Service objects using `new`.

❌ Using field injection for every dependency.

❌ Thinking Beans and Java Objects are the same thing.

❌ Believing Dependency Injection creates new objects for every request.

---

# Interview Notes

### What is a Spring Bean?

A Spring Bean is an object whose lifecycle is managed by the Spring Container.

---

### What is Dependency Injection?

Dependency Injection is the process of supplying required dependencies to a class instead of allowing the class to create them.

---

### What is Inversion of Control?

Inversion of Control is the principle where the framework manages object creation and lifecycle instead of the application code.

---

### Why is Constructor Injection preferred?

- Encourages immutability
- Makes dependencies explicit
- Simplifies unit testing
- Prevents partially initialized objects

---

# FlowState Intuition 💡

Imagine every class says:

> "These are the things I need."

Spring replies:

> "Got it. I'll create them, manage them, and hand them to you."

Classes should declare their dependencies, not construct them.

---

# Lessons Learned

- Beans are Java objects managed by Spring.
- Dependency Injection removes the need for manual object creation.
- Constructor Injection is the preferred approach.
- Spring owns the lifecycle of Beans.
- Dependency Injection separates object creation from object usage.
- IoC shifts responsibility for object creation from application code to the Spring framework.

---

# Anish's Notes

(To be filled during development.)