package com.josiassantos.todolist.user.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.josiassantos.todolist.user.User;
import com.josiassantos.todolist.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) throws Exception {
        var passwordEncrypted = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(passwordEncrypted);
        var alreadyHasUser = findUserByUsername(user.getUsername());

        if (alreadyHasUser != null) {
            throw new Exception("Usuário já existe.");
        } else {
            return userRepository.save(user);
        }
    }

    public User findUserByUsername(String userName) {
        return userRepository.findUserByUsername(userName);
    }
}
