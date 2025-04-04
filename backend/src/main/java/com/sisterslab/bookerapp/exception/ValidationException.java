package com.sisterslab.bookerapp.exception;

//Special error class to throw when validation errors
public class ValidationException extends RuntimeException{

    public ValidationException(String message) {
        super(message);
    }

}
