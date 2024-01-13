package com.semicolon.africa.Todo.dtos.requests;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
