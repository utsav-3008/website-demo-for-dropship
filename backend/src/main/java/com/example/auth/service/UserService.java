package com.example.auth.service;

import com.example.auth.entity.User;
import com.example.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public User register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        return repo.save(user);
    }

    public boolean authenticate(String username, String password) {
        return repo.findByUsername(username)
                .map(u -> encoder.matches(password, u.getPassword()))
                .orElse(false);
    }
}
