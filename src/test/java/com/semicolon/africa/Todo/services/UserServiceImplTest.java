package com.semicolon.africa.Todo.services;

import com.semicolon.africa.Todo.data.repositories.UserRepository;
import com.semicolon.africa.Todo.dtos.requests.AddTaskRequest;
import com.semicolon.africa.Todo.dtos.requests.LoginRequest;
import com.semicolon.africa.Todo.dtos.requests.RegisterRequest;
import com.semicolon.africa.Todo.exceptions.InvalidDetailsException;
import com.semicolon.africa.Todo.exceptions.UserExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private  UserService userService;
    @Autowired
    private UserRepository userRepository;



    @AfterEach
    public void doThisAfterEachRest(){
        userRepository.deleteAll();
    }

    @Test
    public void registerUser_RegisterUserAgain_throwExceptionTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("password");
        registerRequest.setUsername("joy");
        userService.register(registerRequest);
        assertThrows(UserExistException.class, ()-> userService.register(registerRequest));
    }

    @Test
    public void registerUser_countIsOneTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        assertEquals(0, userRepository.count());
        registerRequest.setPassword("password");
        registerRequest.setUsername("joy");
        userService.register(registerRequest);
        assertEquals(1, userRepository.count());

    }

    @Test
    public void registerUser_loginWithWrongUsername_throwsExceptionTest(){
        RegisterRequest request = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        request.setPassword("password");
        request.setUsername("joy");
        userService.register(request);
        loginRequest.setUsername("wrongUsername");
        loginRequest.setPassword("password");
        assertThrows(InvalidDetailsException.class,
                ()->userService.login(loginRequest));
    }


    @Test
    public void registerUser_loginWithRightDetails_foundDiaryIsUnlockedTest(){
        RegisterRequest request = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        request.setPassword("password");
        request.setUsername("philip");
        userService.register(request);
        boolean isLocked = userService.findUserWith("philip").isLocked();
        assertTrue(isLocked);


        loginRequest.setUsername("philip");
        loginRequest.setPassword("password");
        userService.login(loginRequest);
        isLocked = userService.findUserWith("philip").isLocked();
        assertFalse(isLocked);
    }

    @Test
    public void registerUser_loginWithRightDetails_createTask_viewAllTaskSizeIsOneTest(){
        RegisterRequest request = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        AddTaskRequest addTaskRequest = new AddTaskRequest();
        request.setPassword("password");
        request.setUsername("philip");
        userService.register(request);

        loginRequest.setPassword("password");
        loginRequest.setUsername("philip");
        userService.login(loginRequest);

        addTaskRequest.setUsername("philip");
        addTaskRequest.setMessage("Am so fucking tired this morning");
        addTaskRequest.setTitle("title");
        userService.addTask(addTaskRequest);
        userService.addTask(addTaskRequest);
        assertEquals(2, userService.findTasksBelongingTo("philip").size());
    }

    @Test
    void addTwoTask_deleteOneTask_taskIsOneTest(){
        RegisterRequest request = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        AddTaskRequest addTaskRequest = new AddTaskRequest();
        request.setPassword("password");
        request.setUsername("philip");
        userService.register(request);

        loginRequest.setPassword("password");
        loginRequest.setUsername("philip");
        userService.login(loginRequest);

        addTaskRequest.setUsername("philip");
        addTaskRequest.setMessage("Am so  tired this morning");
        addTaskRequest.setTitle("title3");
        userService.addTask(addTaskRequest);

        addTaskRequest.setUsername("philip");
        addTaskRequest.setMessage("please work");
        addTaskRequest.setTitle("title4");
        userService.addTask(addTaskRequest);

        assertEquals(2, userService.findTasksBelongingTo("philip").size());
        userService.deleteTask("title3");
        assertEquals(1, userService.findTasksBelongingTo("philip").size());

    }

    @Test
    void testThatAllUsersTaskCanBeDeleted(){
        RegisterRequest request = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        AddTaskRequest taskRequest = new AddTaskRequest();
        request.setPassword("Ender201");
        request.setUsername("Joy");
        userService.register(request);

        loginRequest.setPassword("Ender201");
        loginRequest.setUsername("Joy");
        userService.login(loginRequest);


        taskRequest.setMessage("Fix my nails");
        taskRequest.setTitle("Stress");
        taskRequest.setUsername("Joy");
        userService.addTask(taskRequest);

        userService.deleteAllTask("Joy");
        assertEquals(0, userRepository.count());
    }

    @Test
    public void testThatTaskCanBeUpdated(){
        RegisterRequest request = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        AddTaskRequest taskRequest = new AddTaskRequest();
        request.setPassword("Ender20");
        request.setUsername("Joseph");
        userService.register(request);

        loginRequest.setPassword("Ender20");
        loginRequest.setUsername("Joseph");
        userService.login(loginRequest);


        taskRequest.setMessage("sleeepppp");
        taskRequest.setTitle("tired");
        taskRequest.setUsername("Joseph");
        userService.addTask(taskRequest);

        userService.updateTask("1", "overTired", "sick");
        
    }



}