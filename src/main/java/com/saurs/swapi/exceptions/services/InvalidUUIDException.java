package com.saurs.swapi.exceptions.services;

public class InvalidUUIDException extends RuntimeException {
    public InvalidUUIDException(Object uuid) {
        super("This UUID is invalid: " + uuid);
    }
}
