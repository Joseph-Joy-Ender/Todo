package com.semicolon.africa.Todo.utils;

import com.semicolon.africa.Todo.data.models.User;
import com.semicolon.africa.Todo.dtos.requests.RegisterRequest;

public class Mapper {

    public static User map(RegisterRequest registerRequest){
        User newUser = new User();

        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(registerRequest.getPassword());
        return newUser;
    }
}
