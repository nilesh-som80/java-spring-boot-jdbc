package com.cms.backend.graphql.dto;

import com.cms.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseSingleUser {
    private String status;
    private String message;
    private User data;
}
