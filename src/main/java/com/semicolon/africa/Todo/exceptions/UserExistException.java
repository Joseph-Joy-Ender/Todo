package com.semicolon.africa.Todo.exceptions;

public class UserExistException extends RuntimeException{
    public UserExistException(String message){
        super(message);
    }
}
