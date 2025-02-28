package com.example.junitandmockito.exception;

public class OverloadException extends RuntimeException{

    public OverloadException(String message){
        super(message);
    }

    public OverloadException(){
        super("overloaded product");
    }
}
