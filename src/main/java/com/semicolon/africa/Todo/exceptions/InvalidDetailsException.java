package com.semicolon.africa.Todo.exceptions;

public class InvalidDetailsException extends RuntimeException{
    public InvalidDetailsException(String message){
        super(message);
    }
}
