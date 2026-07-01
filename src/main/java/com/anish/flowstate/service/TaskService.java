package com.anish.flowstate.service;

import com.anish.flowstate.model.Task;

public class TaskService {
    public Task getTask() {
        return new Task(
                1,
                "Learn Spring Boot",
                "Complete Sprint 2",
                false
        );
    }
}
