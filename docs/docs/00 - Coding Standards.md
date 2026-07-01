# FlowState Coding Standards

> Version: 1.0
>
> This document defines the coding and architectural conventions followed throughout the FlowState project.
>
> The objective is consistency, readability, maintainability, and production-quality code.

---

# 1. Java Naming Conventions

## Classes

Use **PascalCase**.

Examples:

```java
Task
TaskController
TaskService
TaskRepository
FlowStateApplication
```

---

## Interfaces

Use PascalCase.

```java
TaskRepository
TaskService
```

Avoid prefixes like:

```
ITaskService
IUserRepository
```

---

## Variables

Use **camelCase**.

```java
taskId
projectName
createdAt
isCompleted
```

Avoid:

```
task_id
TaskID
TASK_ID
```

---

## Methods

Use camelCase.

Methods should begin with a verb.

Examples:

```java
createTask()

updateTask()

deleteTask()

findTaskById()

getAllTasks()
```

---

## Constants

Use uppercase with underscores.

```java
MAX_RETRY_COUNT

DEFAULT_PAGE_SIZE

API_VERSION
```

---

## Packages

Always lowercase.

```
controller

service

repository

model

config

exception

dto

security

util
```

---

# 2. Database Naming Conventions

## Tables

Use lowercase plural names.

```
tasks

users

projects

notes
```

---

## Primary Keys

Use descriptive IDs.

```
task_id

user_id

project_id

note_id
```

Avoid generic names like:

```
id

fs_id

pk
```

---

## Foreign Keys

Use the referenced entity name.

```
user_id

project_id

task_id
```

---

## Columns

Use snake_case.

```
created_at

updated_at

is_completed

due_date
```

---

# 3. API Naming Conventions

Use plural resource names.

```
/tasks

/users

/projects
```

Examples

```
GET     /tasks

GET     /tasks/{id}

POST    /tasks

PUT     /tasks/{id}

DELETE  /tasks/{id}
```

---

# 4. JSON Naming

Use camelCase.

Example

```json
{
    "taskId": 1,
    "title": "Learn Spring Boot",
    "completed": false
}
```

---

# 5. Git Commit Convention

Every commit should represent one logical unit of work.

Good examples

```
Initial Spring Boot project setup

Add Spring Boot fundamentals documentation

Implement Task model

Create TaskController

Add GET tasks endpoint

Introduce service layer

Integrate Spring Data JPA
```

Avoid

```
update

changes

latest

final

working

fix
```

---

# 6. Project Structure

```
controller

service

repository

model

config

exception

dto

security

util
```

Each package should have a single responsibility.

---

# 7. General Rules

- One public class per file.
- Keep methods short and focused.
- Prefer readability over cleverness.
- Write self-explanatory code.
- Refactor instead of duplicating logic.
- Follow Java naming conventions unless explicitly documented otherwise.

---

# FlowState Principle

> Every decision should be intentional.

If a convention is broken, document why.

Consistency is more valuable than personal preference.