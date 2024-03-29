package com.josiassantos.todolist.user.repository;

import com.josiassantos.todolist.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findUserByUsername(String userName);
}
