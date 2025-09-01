package com.cms.backend.service;

import com.cms.backend.exception.UserNotFoundException;
import com.cms.backend.model.User;
import com.cms.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserService(UserRepository userRepository, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
        this.initializeTable(); // Ensure table exists
    }

    public void initializeTable() {
        try {
            String sql = "SELECT 1 FROM users LIMIT 1";
            jdbcTemplate.queryForList(sql);
        } catch (Exception e) {
            this.userRepository.createTable();
        }
    }

    // CREATE
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // READ all
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // READ by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    // UPDATE
    public void updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        existingUser.setStatus(user.getStatus());

        userRepository.update(existingUser);
    }

    // DELETE
    public void deleteUser(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }
}
