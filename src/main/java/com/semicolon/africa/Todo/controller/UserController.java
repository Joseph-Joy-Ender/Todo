package com.semicolon.africa.Todo.controller;

import com.semicolon.africa.Todo.dtos.requests.AddTaskRequest;
import com.semicolon.africa.Todo.dtos.requests.LoginRequest;
import com.semicolon.africa.Todo.dtos.requests.RegisterRequest;
import com.semicolon.africa.Todo.dtos.responses.*;
import com.semicolon.africa.Todo.exceptions.InvalidDetailsException;
import com.semicolon.africa.Todo.exceptions.MethodArgumentException;
import com.semicolon.africa.Todo.exceptions.TaskException;
import com.semicolon.africa.Todo.exceptions.UserExistException;
import com.semicolon.africa.Todo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
@Autowired
    private  UserService userService;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest){
     RegisterResponse registerResponse = new RegisterResponse();
     try {
        userService.register(registerRequest);
        registerResponse.setMessage("Account successfully created");
        return new ResponseEntity<>(new ApiResponse(true, registerResponse), HttpStatus.CREATED);
     }
     catch (UserExistException exception){
         registerResponse.setMessage(exception.getMessage());
         return new ResponseEntity<>(new ApiResponse(false, registerResponse), HttpStatus.BAD_REQUEST);
     }
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest){
        LoginResponse loginResponse = new LoginResponse();
        try {
            userService.login(loginRequest);
            loginResponse.setMessage("You have successfully logged in");
            return new ResponseEntity<>(new ApiResponse(true, loginResponse), HttpStatus.OK);
        }
        catch (InvalidDetailsException exception){
            loginResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,loginResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("addTask")
    public ResponseEntity<?> addTask(@RequestBody AddTaskRequest addTaskRequest){
        AddTaskResponse addTaskResponse = new AddTaskResponse();
        try {
           userService.addTask(addTaskRequest);
           addTaskResponse.setMessage("Task has been added");
           return new ResponseEntity<>(new ApiResponse(true, addTaskResponse), HttpStatus.CREATED);
        }
        catch (UserExistException exception){
            addTaskResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, addTaskResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("findTask/{username}")
    public Object findTaskBelongingTo(@PathVariable("username") String username){
        try {
            return userService.findTasksBelongingTo(username);
        }
        catch (UserExistException | TaskException e){
            return e.getMessage();
        }
    }

    @DeleteMapping("deleteAllTask/{username}")
    public ResponseEntity<?> deleteAllTask(@PathVariable String username){
        DeleteTaskResponse deleteTaskResponse = new DeleteTaskResponse();
        try {
            userService.deleteAllTask(username);
            deleteTaskResponse.setMessage("Tasks has been deleted");
            return new ResponseEntity<>(new ApiResponse(true, deleteTaskResponse), HttpStatus.OK);

        }
        catch (UserExistException e){
            deleteTaskResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, deleteTaskResponse), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("deleteATask/{title}")
    public ResponseEntity<?> deleteATask(@PathVariable String title){
        DeleteTaskResponse deleteTaskResponse = new DeleteTaskResponse();
        try {
            userService.deleteTask(title);
            deleteTaskResponse.setMessage("Task with this title has been deleted");
            return new ResponseEntity<>(new ApiResponse(true, deleteTaskResponse), HttpStatus.OK);

        }
        catch (UserExistException exception){
            deleteTaskResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, deleteTaskResponse), HttpStatus.NOT_FOUND);
        }
    }




}
