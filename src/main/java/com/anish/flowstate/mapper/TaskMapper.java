package com.anish.flowstate.mapper;

import com.anish.flowstate.dto.TaskRequest;
import com.anish.flowstate.dto.TaskResponse;
import com.anish.flowstate.model.Task;

public class TaskMapper {
    public static TaskResponse toResponse(Task task) {

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted()
        );
    }
    public static Task toEntity(TaskRequest request){

        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCompleted(false);

        return task;

    }


    public static void updateEntity(Task existingTask, Task updatedTask) {
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
    }
}
