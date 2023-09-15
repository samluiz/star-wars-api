package com.saurs.swapi.exceptions.services;

public class CannotBeNullException extends RuntimeException {
    public CannotBeNullException(String msg) {
        super(msg);
    }
}
