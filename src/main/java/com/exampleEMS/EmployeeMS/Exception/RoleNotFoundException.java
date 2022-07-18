package com.exampleEMS.EmployeeMS.Exception;

public class RoleNotFoundException extends RuntimeException{
    int r_id;
    public RoleNotFoundException(int role_id) {
        super("Role not found");
        this.r_id=role_id;
    }
}
