package com.anish.flowstate.repository;

import com.anish.flowstate.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
