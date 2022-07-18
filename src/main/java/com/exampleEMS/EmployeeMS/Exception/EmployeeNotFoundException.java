package com.exampleEMS.EmployeeMS.Exception;

public class EmployeeNotFoundException extends RuntimeException {
    int e_id;
    String email;

    public EmployeeNotFoundException(int e_id) {
        super("Employee not found");
        this.e_id = e_id;
    }

    public EmployeeNotFoundException(String email) {
        super("Employee not found");
        this.email = email;
    }

}