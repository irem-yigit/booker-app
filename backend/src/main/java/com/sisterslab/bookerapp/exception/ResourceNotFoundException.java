package com.sisterslab.bookerapp.exception;

//Special error class to throw when resource not found errors
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }
}
