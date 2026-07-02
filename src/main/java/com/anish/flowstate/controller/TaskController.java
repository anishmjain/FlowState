package com.anish.flowstate.controller;

import com.anish.flowstate.model.Task;
import com.anish.flowstate.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController{
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public Task getTask() {
        return taskService.getTask();

    }
}