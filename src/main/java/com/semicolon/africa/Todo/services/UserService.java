package com.semicolon.africa.Todo.services;

import com.semicolon.africa.Todo.data.models.Task;
import com.semicolon.africa.Todo.data.models.User;
import com.semicolon.africa.Todo.dtos.requests.AddTaskRequest;
import com.semicolon.africa.Todo.dtos.requests.LoginRequest;
import com.semicolon.africa.Todo.dtos.requests.RegisterRequest;

import java.util.List;

public interface UserService {
    void register(RegisterRequest registerRequest);
    void login(LoginRequest loginRequest);
    User findUserWith(String username);
    void addTask(AddTaskRequest addTaskRequest);
   void deleteTask(String title);
   void deleteAllTask(String username);
   void updateTask(String id, String title, String message);
   List<Task> findTasksBelongingTo(String username);
}
