package com.cms.backend.repository;

import com.cms.backend.model.User;
import com.cms.backend.model.enums.UserRole;
import com.cms.backend.model.enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper to map DB row → User object
    private static final RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    UserRole.valueOf(rs.getString("role")),
                    UserStatus.valueOf(rs.getString("status"))
            );
        }
    };

    // Create users table if not exists
    public void createTable() {
        String sql = """
                CREATE TABLE users (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      email VARCHAR(100) NOT NULL UNIQUE,
                      role ENUM('ADMIN','EDITOR','VIEWER') NOT NULL,
                      status ENUM('ACTIVE','INACTIVE') NOT NULL
                  );
            """;
        jdbcTemplate.execute(sql);
    }

    // Create user
    public User save(User user) {
        String sql = "INSERT INTO users (name, email, role, status) VALUES (?, ?, ?, ?)";
        // use KeyHolder to fetch generated ID
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getRole().name());
            ps.setString(4, user.getStatus().name());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            user.setId(key.longValue());
        } else {
            throw new IllegalStateException("Failed to retrieve generated ID for user: " + user.getEmail());
        }
        return user;

    }


    // Get all users
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    // Get user by ID
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper, id);
        return users.stream().findFirst();
    }

    // Update user
    public void update(User user) {
        String sql = "UPDATE users SET name = ?, email = ?, role = ?, status = ? WHERE id = ?";
         jdbcTemplate.update(sql,
                user.getName(),
                user.getEmail(),
                user.getRole().name(),
                user.getStatus().name(),
                user.getId());
    }

    // Delete user (return void, not int)
    public void deleteById(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
