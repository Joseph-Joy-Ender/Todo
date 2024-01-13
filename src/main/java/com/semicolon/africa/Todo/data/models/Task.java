package com.semicolon.africa.Todo.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Document
@Data
public class Task {
    @Id
    private String id;
    private String title;
    private String message;
    private boolean isComplete;
    private LocalDateTime localDateTime = LocalDateTime.now();
    private String userId;
}
