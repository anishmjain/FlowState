package com.anish.flowstate.repository;

import com.anish.flowstate.model.Task;
import com.anish.flowstate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByOwner(User owner);
    Optional<Task> findByIdAndOwner(Integer id, User owner);
}
