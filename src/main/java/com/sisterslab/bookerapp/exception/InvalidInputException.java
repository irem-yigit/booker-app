package com.sisterslab.bookerapp.exception;

public class InvalidInputException extends RuntimeException{
    InvalidInputException(String message){
        super(message);
    }
}
