package com.cms.backend.graphql.dto;

import com.cms.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public  class ResponseUserList {
    private String status;
    private String message;
    private List<User> data;
}
