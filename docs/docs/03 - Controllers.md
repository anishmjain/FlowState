# FlowState Documentation

# Chapter 3 - Controllers

**Sprint:** 2

---

# Objective

Understand how Spring Boot receives HTTP requests and returns responses.

---

# What is a Controller?

A Controller acts as the entry point of the application.

Its responsibilities are:

- Receive HTTP requests
- Call the appropriate Service
- Return the response

A Controller should **not** contain business logic.

---

# @RestController

```java
@RestController
```

Marks a class as a REST Controller.

When Spring starts:

- It discovers this class through Component Scanning.
- Creates a Bean.
- Registers it to handle HTTP requests.

Without this annotation, Spring ignores the class completely.

---

# @RequestMapping

```java
@RequestMapping("/tasks")
```

Defines the base URL for every endpoint inside the controller.

Instead of writing:

```java
@GetMapping("/tasks")
@PostMapping("/tasks")
```

we write:

```java
@RequestMapping("/tasks")
```

and then

```java
@GetMapping
```

Cleaner and easier to maintain.

---

# @GetMapping

```java
@GetMapping
```

Maps an HTTP GET request to a Java method.

Example:

```java
@GetMapping
public String getTask() {

    return "FlowState is alive!";

}
```

Browser

↓

GET /tasks

↓

Controller

↓

Response

---

# Returning Java Objects

Instead of returning:

```java
String
```

we can return:

```java
Task
```

Example:

```java
@GetMapping
public Task getTask() {

    return new Task(
            1,
            "Learn Spring Boot",
            "Complete Sprint 2",
            false
    );

}
```

Spring automatically converts this object into JSON.

---

# JSON Serialization

When a Controller returns an object:

```
Task
```

Spring delegates serialization to **Jackson**.

Flow:

```
Task Object

↓

Jackson

↓

JSON

↓

Browser
```

Example Response

```json
{
    "id": 1,
    "title": "Learn Spring Boot",
    "description": "Complete Sprint 2",
    "completed": false
}
```

No manual JSON creation is required.

---

# Request Flow

```
Browser

↓

DispatcherServlet

↓

TaskController

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

# Engineering Notes

A Controller should only coordinate requests and responses.

It should not:

- Perform business logic
- Validate complex rules
- Access the database directly

Those responsibilities belong to the Service Layer.

Think of the Controller as a receptionist.

It accepts requests, forwards them to the correct component, and returns the response.

---

# Common Mistakes

❌ Missing @RestController

❌ Missing @GetMapping

❌ Placing Controllers outside the component scan package

❌ Writing business logic inside Controllers

---

# Interview Notes

### Difference between @Controller and @RestController

@Controller returns Views.

@RestController returns Response Bodies (JSON, XML, Strings, etc.).

---

### What does @GetMapping do?

Maps HTTP GET requests to Java methods.

---

### What is Component Scanning?

Spring scans packages for annotated classes and automatically creates Beans.

---

# Lessons Learned

- Controllers receive HTTP requests.
- @RestController registers a REST endpoint.
- @RequestMapping defines a common URL.
- @GetMapping maps GET requests.
- Spring automatically converts Java objects into JSON using Jackson.

---

# Anish's Notes

```
(To be filled by me during development.)
```