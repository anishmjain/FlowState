package com.anish.flowstate.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "FS_TASKS")
public class Task{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FS_ID")
    private  Integer id;

    @NotBlank(message = "Title cannot be empty.")
    @Size(max = 100, message = "Title cannot exceed 100 characters.")
    @Column(name = "FS_TITLE")
    public String title;
    @NotBlank(message = "Description cannot be empty.")
    @Size(max = 500, message = "Description cannot exceed 500 characters.")
    @Column(name = "FS_DESCRIPTION")
    private String description;

    @Column(name = "IS_COMPLETED")
    private Boolean  completed;

    public Task(String title, String description, Boolean  completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public Task() {
    }

    public Integer  getId() {
        return id;
    }

    public void setId(Integer  id) {
        this.id = id;
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

    public Boolean  isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean  completed) {
        this.completed = completed;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }
}