package com.example.api_nosql.exception;

public class IlegalStatusChange extends RuntimeException{

    public IlegalStatusChange(String message){
        super(message);
    }
}
