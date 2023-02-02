package com.capgemini.employeeassets.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SerialNumberNotFoundException extends RuntimeException{
    public SerialNumberNotFoundException(String message)
    {
        super(message);
    }
}
