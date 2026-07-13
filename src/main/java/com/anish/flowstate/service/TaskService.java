package com.anish.flowstate.service;

import com.anish.flowstate.dto.TaskSearchCriteria;
import com.anish.flowstate.exception.TaskNotFoundException;
import com.anish.flowstate.mapper.TaskMapper;
import com.anish.flowstate.model.Priority;
import com.anish.flowstate.model.Task;
import com.anish.flowstate.model.User;
import com.anish.flowstate.repository.TaskRepository;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mysql.cj.conf.PropertyKey.logger;

@Service
public class TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public Page<Task> getTasks(TaskSearchCriteria criteria){
        User owner = userService.getCurrentUser();
        Pageable pageable = buildPageable(criteria);
        Priority priority = criteria.getPriority();
        if (priority == null) {
            logger.info("Fetching all tasks for {}", owner.getUsername());
            return taskRepository.findByOwner(owner,pageable);
        }
        logger.info("Fetching {} priority tasks for {}",
                priority,
                owner.getUsername());
        return taskRepository.findByOwnerAndPriority(owner, priority, pageable);
    }

    private Pageable buildPageable(TaskSearchCriteria criteria) {
        Sort.Direction sortDirection = Sort.Direction.fromString(criteria.getDirection());
        return PageRequest.of(criteria.getPage(), criteria.getSize() ,
                Sort.by(sortDirection, criteria.getSortBy()));
    }

    public Task createTask(Task task) {
        User owner = userService.getCurrentUser();
        task.setOwner(owner);
        return taskRepository.save(task);
    }

    public Task getTaskById(Integer id) {
        User owner = userService.getCurrentUser();
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
        logger.info("Deleting task {}", id);
        Task task = getOwnedTask(id);
        taskRepository.delete(task);
    }

    public Task completeTask(Integer id) {
        Task task = getOwnedTask(id);
        task.setCompleted(true);
        return taskRepository.save(task);
    }

    private Task getOwnedTask(Integer id) {
        User owner = userService.getCurrentUser();
        return taskRepository
                .findByIdAndOwner(id, owner)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

}
