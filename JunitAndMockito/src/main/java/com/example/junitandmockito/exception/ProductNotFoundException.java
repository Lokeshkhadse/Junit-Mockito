package com.example.junitandmockito.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message){
        super(message);
    }

    public ProductNotFoundException(){
        super("Product not found");
    }
}
