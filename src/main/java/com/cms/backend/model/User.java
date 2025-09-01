package com.cms.backend.model;

import com.cms.backend.model.enums.UserRole;
import com.cms.backend.model.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                       // generates getters, setters, toString, equals, hashCode
@NoArgsConstructor          // generates default constructor
@AllArgsConstructor         // generates constructor with all fields
@Builder
public class User {

    private Long id;  // auto-generated in DB, so no validation needed

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "Role is required")
    private UserRole role;   // Maps to MySQL ENUM('ADMIN','EDITOR','VIEWER')

    @NotNull(message = "Status is required")
    private UserStatus status; // Maps to MySQL ENUM('ACTIVE','INACTIVE')
}
