package com.saurs.swapi.exceptions.services;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Object id) {
        super("Resource not found. ID: " + id);
    }
}
