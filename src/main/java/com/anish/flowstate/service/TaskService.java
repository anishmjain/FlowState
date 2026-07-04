package com.anish.flowstate.service;

import com.anish.flowstate.dto.TaskRequest;
import com.anish.flowstate.exception.TaskNotFoundException;
import com.anish.flowstate.mapper.TaskMapper;
import com.anish.flowstate.model.Task;
import com.anish.flowstate.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private static final Logger logger =
            LoggerFactory.getLogger(TaskService.class);
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        logger.info("Fetching all tasks.");
        return repository.findAll();
    }

    public Task createTask(Task task) {
        logger.info("Creating task: {}", task.getTitle());
        return repository.save(task);
    }

    public Task getTaskById(Integer id) {
        logger.info("Fetching task with id {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Task {} not found.", id);
                    return new TaskNotFoundException(id);
                });
    }

    public Task updateTask(Integer id, Task updatedTask){
        logger.info("Updating task {}", id);
        Task existingTask = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        TaskMapper.updateEntity(existingTask, updatedTask);
        return repository.save(existingTask);
    }

    public void deleteTask(Integer id){
        logger.info("Deleting task {}", id);
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
