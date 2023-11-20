package com.josiassantos.todolist.task;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(length = 50)
    private String title;

    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;
    private UUID idUser;

    public void setTitle(String title) throws Exception {
        if (title.length() > 50) {
            throw new Exception("Título com mais de 50 caracteres.");
        }
        this.title = title;
    }
}
