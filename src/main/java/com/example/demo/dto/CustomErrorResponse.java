package com.example.demo.dto;

import lombok.Data;

@Data
public class CustomErrorResponse {
    private ErrorDetails error;

    @Data
    public static class ErrorDetails {
        private String name;
        private String message;
    }

    public String getMessage() {
        return error != null ? error.getMessage() : "unknown error occurred";
    }
}
