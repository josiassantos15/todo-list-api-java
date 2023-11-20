package com.josiassantos.todolist.task.repository;

import com.josiassantos.todolist.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
}
