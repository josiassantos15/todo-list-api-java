package com.josiassantos.todolist.task.service;

import com.josiassantos.todolist.task.Task;
import com.josiassantos.todolist.task.repository.TaskRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTaskService(Task task, HttpServletRequest request) {
        task.setIdUser((UUID) request.getAttribute("idUser"));
        return taskRepository.save(task);
    }
}
