package com.sisterslab.goodreadsapp.exception;

public class InvalidInputException extends RuntimeException{
    InvalidInputException(String message){
        super(message);
    }
}
