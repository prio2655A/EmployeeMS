package com.exampleEMS.EmployeeMS.Exception;

public class OrganisationNotFoundException extends RuntimeException{
    int o_id;
    public OrganisationNotFoundException(int org_Id) {
        super("Organisation not found");
        o_id=org_Id;
    }
}
