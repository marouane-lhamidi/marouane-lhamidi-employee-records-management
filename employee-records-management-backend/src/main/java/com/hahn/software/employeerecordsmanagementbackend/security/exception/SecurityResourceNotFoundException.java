package com.hahn.software.employeerecordsmanagementbackend.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SecurityResourceNotFoundException extends RuntimeException{
    public SecurityResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with the given input data %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
