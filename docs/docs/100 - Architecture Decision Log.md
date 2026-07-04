## Decision #1

DTOs instead of exposing entities.

Reason:
Avoid exposing internal persistence models and allow API evolution independently of the database schema.

---

## Decision #2

Constructor Injection

Reason:
Promotes immutability, clearer dependencies, and easier unit testing.

---

## Decision #3

Manual Mapper before MapStruct

Reason:
Understand the mapping process before introducing automation.