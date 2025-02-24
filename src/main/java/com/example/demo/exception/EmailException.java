package com.example.demo.exception;

public class EmailException extends Exception{

    public EmailException() {
        super("email have not good structure");
    }
}