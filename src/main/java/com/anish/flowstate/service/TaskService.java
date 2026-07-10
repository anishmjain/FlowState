package com.anish.flowstate.service;

import com.anish.flowstate.dto.TaskRequest;
import com.anish.flowstate.exception.TaskNotFoundException;
import com.anish.flowstate.mapper.TaskMapper;
import com.anish.flowstate.model.Task;
import com.anish.flowstate.model.User;
import com.anish.flowstate.repository.TaskRepository;
import com.anish.flowstate.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository repository, UserRepository userRepository) {
        this.taskRepository = repository;
        this.userRepository = userRepository;
    }

    public List<Task> getAllTasks() {
        User owner = getCurrentUser();
        logger.info("Fetching tasks for {}", owner.getUsername());
        return taskRepository.findByOwner(owner);
    }

    public Task createTask(Task task) {
        User owner = getCurrentUser();
        task.setOwner(owner);
        return taskRepository.save(task);
    }

    public Task getTaskById(Integer id) {
        User owner = getCurrentUser();
        logger.info("Fetching task {} for {}", id, owner.getUsername());
        return taskRepository.findByIdAndOwner(id, owner)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task updateTask(Integer id, Task updatedTask){
        logger.info("Updating task {}", id);
        Task existingTask = getOwnedTask(id);
        TaskMapper.updateEntity(existingTask, updatedTask);
        return taskRepository.save(existingTask);
    }

    public void deleteTask(Integer id){
        Task task = getOwnedTask(id);
        taskRepository.delete(task);
    }

    public Task completeTask(Integer id) {
        Task task = getOwnedTask(id);
        task.setCompleted(true);
        return taskRepository.save(task);
    }

    private User getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found."));
    }

    private Task getOwnedTask(Integer id) {
        User owner = getCurrentUser();
        return taskRepository
                .findByIdAndOwner(id, owner)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

}
