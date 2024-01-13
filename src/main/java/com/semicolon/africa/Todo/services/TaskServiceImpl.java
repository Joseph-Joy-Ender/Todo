package com.semicolon.africa.Todo.services;

import com.semicolon.africa.Todo.data.models.Task;
import com.semicolon.africa.Todo.data.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public void create(String title, String message, String userId) {
        Task task = new Task();
        task.setTitle(title);
        task.setMessage(message);
        task.setUserId(userId);
        taskRepository.save(task);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }




}
