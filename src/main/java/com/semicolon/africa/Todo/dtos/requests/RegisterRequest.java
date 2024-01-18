package com.semicolon.africa.Todo.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterRequest {
 @NotBlank
 @NotEmpty
    private String username;
 @NotBlank
 @NotEmpty
    private String password;
}
