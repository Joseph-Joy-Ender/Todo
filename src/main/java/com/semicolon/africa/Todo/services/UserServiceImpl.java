package com.semicolon.africa.Todo.services;

import com.semicolon.africa.Todo.data.models.Task;
import com.semicolon.africa.Todo.data.models.User;
import com.semicolon.africa.Todo.data.repositories.TaskRepository;
import com.semicolon.africa.Todo.data.repositories.UserRepository;
import com.semicolon.africa.Todo.dtos.requests.AddTaskRequest;
import com.semicolon.africa.Todo.dtos.requests.LoginRequest;
import com.semicolon.africa.Todo.dtos.requests.RegisterRequest;
import com.semicolon.africa.Todo.exceptions.InvalidDetailsException;
import com.semicolon.africa.Todo.exceptions.TaskException;
import com.semicolon.africa.Todo.exceptions.UserExistException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.semicolon.africa.Todo.utils.Mapper.map;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository repository;
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final TaskService taskService;
    @Override
    public void register(RegisterRequest registerRequest) {
        if (userExist(registerRequest.getUsername())) throw new UserExistException(registerRequest.getUsername() + " already exist");
        User user = map(registerRequest);
        repository.save(user);
    }

    private boolean userExist(String username) {
        User foundUser = repository.findUserByUsername(username);
        return foundUser != null;
    }

    @Override
    public void login(LoginRequest loginRequest) {
        User foundUser = repository.findUserByUsername(loginRequest.getUsername());
        if (!userExist(loginRequest.getUsername())) throw new InvalidDetailsException("Wrong information");
        if (!foundUser.getPassword().equals(loginRequest.getPassword())) throw new InvalidDetailsException("Wrong data");
        foundUser.setLocked(false);
        repository.save(foundUser);


    }

    @Override
    public User findUserWith(String username) {
        return repository.findUserByUsername(username);
    }

    @Override
    public void addTask(AddTaskRequest addTaskRequest) {
        User findUser = findUserWith(addTaskRequest.getUsername());
        if (findUser == null) throw new UserExistException("User does not exist");
        taskService.create(addTaskRequest.getTitle(), addTaskRequest.getMessage(), findUser.getId());

    }

    @Override
    public void deleteTask(String title) {
        if (title == null) throw new TaskException("Title not found");
       taskRepository.deleteTaskByTitle(title);
    }

    @Override
    public void deleteAllTask(String username) {
        User findUser = findUserWith(username);
        if (findUser == null) throw new UserExistException("User not found");
        repository.deleteAll();
    }

    @Override
    public void updateTask(String id, String title, String message) {
        Task task = taskRepository.findTaskByTitle(title);
        if (task != null) {
            task.setTitle(title);
            task.setMessage(message);
        }
    }



    @Override
    public List<Task> findTasksBelongingTo(String username) {
        User findUser = repository.findUserByUsername(username);
        if (findUser == null) throw new UserExistException("User not found");
        List<Task> tasks = new ArrayList<>();
        for (Task task: taskService.findAll()) {
            if (task == null) throw new TaskException("No task found");
            if (findUser.getId().equals(task.getUserId())) tasks.add(task);

        }
        return tasks;
    }

}
