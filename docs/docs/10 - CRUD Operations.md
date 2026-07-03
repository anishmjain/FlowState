# 11 - CRUD Operations.md

# CRUD Operations using Spring Data JPA

CRUD stands for:

```
Create

Read

Update

Delete
```

These four operations form the foundation of most backend APIs.

---

# Create

Service

```java
public Task createTask(Task task){
    return repository.save(task);
}
```

Controller

```java
@PostMapping
public ResponseEntity<Task> createTask(
        @Valid @RequestBody Task task){

    Task createdTask = taskService.createTask(task);

    return ResponseEntity.status(HttpStatus.CREATED)
            .body(createdTask);
}
```

Response

```
201 Created
```

---

# Read All

Service

```java
public List<Task> getAllTasks(){
    return repository.findAll();
}
```

Controller

```java
@GetMapping
public List<Task> getAllTasks(){
    return taskService.getAllTasks();
}
```

---

# Read by ID

Service

```java
public Task getTaskById(Integer id){

    return repository.findById(id)
            .orElseThrow(() ->
                    new TaskNotFoundException(id));

}
```

Controller

```java
@GetMapping("/{id}")
public ResponseEntity<Task> getTaskById(
        @PathVariable Integer id){

    return ResponseEntity.ok(
            taskService.getTaskById(id));

}
```

---

# Update

Always fetch the existing entity first.

```java
Task existingTask = repository.findById(id)
        .orElseThrow(() ->
                new TaskNotFoundException(id));

existingTask.setTitle(updatedTask.getTitle());
existingTask.setDescription(updatedTask.getDescription());
existingTask.setCompleted(updatedTask.isCompleted());

return repository.save(existingTask);
```

Why?

The request body does not identify the resource.

The URL identifies **which resource** to update.

The body contains **what should change**.

---

# Delete

Service

```java
public void deleteTask(Integer id){
    repository.deleteById(id);
}
```

Controller

```java
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteTask(
        @PathVariable Integer id){

    taskService.deleteTask(id);

    return ResponseEntity.noContent().build();

}
```

Response

```
204 No Content
```

---

# REST Endpoints

```
POST    /tasks

GET     /tasks

GET     /tasks/{id}

PUT     /tasks/{id}

DELETE  /tasks/{id}
```

---

# Standard HTTP Status Codes

```
200 OK
```

Successful GET and UPDATE.

```
201 Created
```

Resource successfully created.

```
204 No Content
```

Resource successfully deleted.

```
400 Bad Request
```

Validation failure.

```
404 Not Found
```

Requested resource does not exist.

---

# Complete Request Flow

```
Client

↓

Controller

↓

Service

↓

Repository

↓

Hibernate

↓

MySQL

↓

ResponseEntity

↓

Client
```

---

# FlowState Takeaway

CRUD is not simply calling repository methods.

A production-quality CRUD API should include:

- Layered Architecture
- Proper HTTP Status Codes
- Validation
- Global Exception Handling
- Clean separation of concerns

## Key Rule

> Controllers handle HTTP, Services contain business logic, Repositories handle persistence, and Hibernate translates object operations into SQL.