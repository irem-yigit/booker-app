package com.sisterslab.goodreadsapp.exception;

public class BookShelfNotFoundException extends RuntimeException{

    public BookShelfNotFoundException(String message) {
        super(message);
    }
}
