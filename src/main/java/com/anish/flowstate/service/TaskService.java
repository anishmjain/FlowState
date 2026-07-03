package com.anish.flowstate.service;

import com.anish.flowstate.exception.TaskNotFoundException;
import com.anish.flowstate.model.Task;
import com.anish.flowstate.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task createTask(Task task) {
        return repository.save(task);
    }

    public Task getTaskById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task updateTask(Integer id, Task updatedTask) {

        Task existingTask = repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));


        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setCompleted(updatedTask.isCompleted());

        return repository.save(existingTask);
    }

    public void deleteTask(Integer id){
        repository.deleteById(id);
    }
}
