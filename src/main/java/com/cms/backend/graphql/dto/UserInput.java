package com.cms.backend.graphql.dto;

import com.cms.backend.model.enums.UserRole;
import com.cms.backend.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInput {
    private String name;
    private String email;
    private UserRole role;
    private UserStatus status;
}
