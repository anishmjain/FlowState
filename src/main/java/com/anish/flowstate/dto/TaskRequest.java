package com.anish.flowstate.dto;

import com.anish.flowstate.model.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskRequest {

    @NotBlank(message = "Title cannot be empty.")
    @Size(max = 100)
    private String title;

    @NotBlank(message = "Description cannot be empty.")
    @Size(max = 500)
    private String description;

    private boolean completed;

    private Priority priority;

    public TaskRequest() {
    }

    public TaskRequest(String title,
                       String description,
                       boolean completed) {

        this.title = title;
        this.description = description;
        this.completed = completed;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
