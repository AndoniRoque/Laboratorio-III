package com.example.demo.exception;

public class NoCategoriesFoundException extends RuntimeException {
    public NoCategoriesFoundException(String message) {
        super(message);
    }
}
