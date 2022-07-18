package com.exampleEMS.EmployeeMS.Service;

import com.exampleEMS.EmployeeMS.Models.Employee;

import java.util.List;

public interface EmployeeService {
//    Employee saveEmployee(Employee employee);
    Employee saveEmployee(Employee employee, int o_id);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int e_id);
    Employee updateEmployee(Employee employee, int e_id);
    void deleteEmployee(int e_id);
}

