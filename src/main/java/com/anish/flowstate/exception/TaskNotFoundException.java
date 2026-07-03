package com.anish.flowstate.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(Integer id) {
        super("Task with ID " + id + " not found.");
    }
}
