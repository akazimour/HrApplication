package com.hr.microservices.EmployeeMs.EmployeeMs.service;

public class NoneUniqueIdException extends RuntimeException{
    public NoneUniqueIdException(Long id) {
        super("The Id is already exist " + id);
    }
}
