package com.semicolon.africa.Todo.dtos.requests;

import lombok.Data;

@Data
public class UpdateRequest {
    private String oldTittle;
    private String newTittle;
    private String message;
    private String username;
}
