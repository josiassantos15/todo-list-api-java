package com.josiassantos.todolist.task.controller;

import com.josiassantos.todolist.task.Task;
import com.josiassantos.todolist.task.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping()
    public ResponseEntity<Task> creteTask(@RequestBody Task task, HttpServletRequest request) {
        Task taskCreated = taskService.createTaskService(task, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskCreated);
    }
}
