package com.semicolon.africa.Todo.data.repositories;

import com.semicolon.africa.Todo.data.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task, String> {
    Task findTaskByTitle(String title);
    void deleteTaskByTitle(String title);


}
