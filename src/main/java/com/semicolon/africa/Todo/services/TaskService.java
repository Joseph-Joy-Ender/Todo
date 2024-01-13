package com.semicolon.africa.Todo.services;

import com.semicolon.africa.Todo.data.models.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    void create(String title, String message, String id);

    List<Task> findAll();



}
