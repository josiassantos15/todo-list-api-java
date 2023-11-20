package com.josiassantos.todolist.task.controller;

import com.josiassantos.todolist.task.Task;
import com.josiassantos.todolist.task.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping()
    public ResponseEntity<Task> creteTask(@RequestBody Task task, HttpServletRequest request) throws Exception {
        Task taskCreated = taskService.createTaskService(task, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskCreated);
    }

    @GetMapping()
    public ResponseEntity<List<Task>> listTasks(HttpServletRequest request) {
        return ResponseEntity.ok(taskService.listTasksByIdUser(request));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@RequestBody Task task, @PathVariable UUID taskId, HttpServletRequest request)
            throws Exception {
        return ResponseEntity.ok(taskService.listTaskById(task, taskId, request));
    }
}
