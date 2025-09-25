package com.example.api_nosql.exception;

public class ExistingMatch extends RuntimeException {
    public ExistingMatch(String message) {
        super(message);
    }
}
