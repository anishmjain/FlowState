package com.anish.flowstate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController{
    @GetMapping("/tasks")
    public String getTasks(){
        return "FlowState is alive!";
    }
}