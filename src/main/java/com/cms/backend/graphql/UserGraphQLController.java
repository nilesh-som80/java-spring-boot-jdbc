package com.cms.backend.graphql;

import com.cms.backend.graphql.dto.ResponseDelete;
import com.cms.backend.graphql.dto.ResponseSingleUser;
import com.cms.backend.graphql.dto.ResponseUserList;
import com.cms.backend.graphql.dto.UserInput;
import com.cms.backend.model.User;
import com.cms.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserGraphQLController {

    private final UserService userService;

    // ----------------- Queries -----------------

    @QueryMapping
    public ResponseSingleUser getUser(@Argument Long id) {
        User user = userService.getUserById(id);
        return ResponseSingleUser.builder()
                .status("success")
                .message("User fetched successfully")
                .data(user)
                .build();
    }

    @QueryMapping
    public ResponseUserList getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseUserList.builder()
                .status("success")
                .message("All users fetched successfully")
                .data(users)
                .build();
    }

    // ----------------- Mutations -----------------

    @MutationMapping
    public ResponseSingleUser createUser(@Argument UserInput input) {
        User user = User.builder()
                .name(input.getName())
                .email(input.getEmail())
                .role(input.getRole())
                .status(input.getStatus())
                .build();
        User savedUser = userService.createUser(user);
        return ResponseSingleUser.builder()
                .status("success")
                .message("User created successfully")
                .data(savedUser)
                .build();
    }

    @MutationMapping
    public ResponseSingleUser updateUser(@Argument Long id, @Argument UserInput input) {
        User user = User.builder()
                .name(input.getName())
                .email(input.getEmail())
                .role(input.getRole())
                .status(input.getStatus())
                .build();
        userService.updateUser(id, user);
        User updatedUser = userService.getUserById(id);
        return ResponseSingleUser.builder()
                .status("success")
                .message("User updated successfully")
                .data(updatedUser)
                .build();
    }

    @MutationMapping
    public ResponseDelete deleteUser(@Argument Long id) {
        userService.deleteUser(id);
        return ResponseDelete.builder()
                .status("success")
                .message("User deleted successfully")
                .data(true)
                .build();
    }

    
}

// ----------------- DTOs / Wrappers -----------------

