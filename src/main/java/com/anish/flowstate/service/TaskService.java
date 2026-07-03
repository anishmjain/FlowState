package com.anish.flowstate.service;

import com.anish.flowstate.dto.TaskRequest;
import com.anish.flowstate.exception.TaskNotFoundException;
import com.anish.flowstate.mapper.TaskMapper;
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

    public Task updateTask(Integer id, Task updatedTask){
        Task existingTask = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        TaskMapper.updateEntity(existingTask, updatedTask);
        return repository.save(existingTask);
    }

    public void deleteTask(Integer id){
        repository.deleteById(id);
    }

    public Task completeTask(Integer id) {
        Task task = repository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(id));

        task.setCompleted(true);

        return repository.save(task);
    }
}
