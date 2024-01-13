package com.semicolon.africa.Todo.dtos.requests;

import lombok.Data;

@Data
public class AddTaskRequest {
    private String title;
    private String message;
    private String username;
}
