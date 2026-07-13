package com.anish.flowstate.repository;

import com.anish.flowstate.model.Priority;
import com.anish.flowstate.model.Task;
import com.anish.flowstate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByOwner(User owner);
    Optional<Task> findByIdAndOwner(Integer id, User owner);
    List<Task> findByOwnerAndPriority(
            User owner,
            Priority priority);

    Page<Task> findByOwner(User owner, Pageable pageable);

    Page<Task> findByOwnerAndPriority(
            User owner,
            Priority priority,
            Pageable pageable
    );

}
