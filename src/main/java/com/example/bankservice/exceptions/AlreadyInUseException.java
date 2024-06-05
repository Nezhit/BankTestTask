package com.example.bankservice.exceptions;

public class AlreadyInUseException extends RuntimeException{
    public AlreadyInUseException(String  message){
        super(message);
    }
}
