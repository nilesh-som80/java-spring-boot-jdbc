package com.cms.backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String status;   // "success", "failure", "error"
    private String message;  // human-readable message
    private T data;          // can be User, List<User>, or null
}
