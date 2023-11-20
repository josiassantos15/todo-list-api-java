package com.josiassantos.todolist.task.service;

import com.josiassantos.todolist.task.Task;
import com.josiassantos.todolist.task.repository.TaskRepository;
import com.josiassantos.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTaskService(Task task, HttpServletRequest request) throws Exception {
        task.setIdUser((UUID) request.getAttribute("idUser"));
        var currentData = LocalDateTime.now();
        if (currentData.isAfter(task.getStartAt()) || currentData.isAfter(task.getEndAt())) {
            throw new Exception("A data de início/término deve ser maior que a data atual.");
        }
        if (task.getStartAt().isAfter(task.getEndAt())) {
            throw new Exception("A data de início deve ser anterior a data de término.");
        }
        return taskRepository.save(task);
    }

    public List<Task> listTasksByIdUser(HttpServletRequest request) {
        return taskRepository.findByIdUser((UUID) request.getAttribute("idUser"));
    }

    public Task listTaskById(Task task, UUID id, HttpServletRequest request) throws Exception {
        var existingTask = taskRepository.findById(id).orElseThrow(() -> new Exception("Tarefa não encontrada"));

        if (!existingTask.getIdUser().equals(request.getAttribute("idUser"))) {
            throw new Exception("O usuário não é dono dessa tarefa.");
        }
        Utils.copyNonNullProperties(task, existingTask);
        return taskRepository.save(existingTask);
    }
}
