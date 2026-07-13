package com.anish.flowstate.controller;

import com.anish.flowstate.dto.TaskRequest;
import com.anish.flowstate.dto.TaskResponse;
import com.anish.flowstate.dto.TaskSearchCriteria;
import com.anish.flowstate.mapper.TaskMapper;
import com.anish.flowstate.model.Priority;
import com.anish.flowstate.model.Task;
import com.anish.flowstate.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController{
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public Page<TaskResponse> getAllTasks(
            @RequestParam(required = false) Priority priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        TaskSearchCriteria criteria = new TaskSearchCriteria();
        criteria.setPriority(priority);
        criteria.setPage(page);
        criteria.setSize(size);
        criteria.setSortBy(sortBy);
        criteria.setDirection(direction);

        return taskService.getTasks(criteria)
                .map(TaskMapper::toResponse);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        Task task = TaskMapper.toEntity(taskRequest);
        Task createdTask = taskService.createTask(task);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TaskMapper.toResponse(createdTask));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Integer id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(TaskMapper.toResponse(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask( @PathVariable Integer id,
                           @RequestBody TaskRequest taskRequest){
        Task updatedTask = taskService.updateTask(id, TaskMapper.toEntity(taskRequest));
        return ResponseEntity.ok(TaskMapper.toResponse(updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TaskResponse> completeTask(
            @PathVariable Integer id){

        Task task = taskService.completeTask(id);

        return ResponseEntity.ok(
                TaskMapper.toResponse(task)
        );
    }
}