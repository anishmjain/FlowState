package com.anish.flowstate.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final TaskService taskService;

    public EmailService(TaskService taskService) {
        this.taskService = taskService;
    }

}
