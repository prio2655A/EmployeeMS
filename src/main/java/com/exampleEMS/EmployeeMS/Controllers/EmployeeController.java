package com.exampleEMS.EmployeeMS.Controllers;

import com.exampleEMS.EmployeeMS.Exception.EmployeeNotFoundException;
import com.exampleEMS.EmployeeMS.Models.Employee;
import com.exampleEMS.EmployeeMS.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/EMS/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    @PostMapping
//    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee)
//    {
//        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
//    }

    @PostMapping("/add/WithO_Id:{o_id}")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee, @PathVariable("o_id") int o_id )
    {
        if(employee.getFirstName().isEmpty())
            return new ResponseEntity<String>("Employee first name cannot be empty.",HttpStatus.NOT_ACCEPTABLE);
        else if(employee.getLastName().isEmpty())
            return new ResponseEntity<String>("Employee last name cannot be empty.",HttpStatus.NOT_ACCEPTABLE);
        else if (employee.getSalary()<=0)
            return new ResponseEntity<String>("Employee salary cannot be empty nor zero.",HttpStatus.NOT_ACCEPTABLE);
        else if (employee.getSalary()<0)
            return new ResponseEntity<String>("Employee salary cannot be negative.",HttpStatus.NOT_ACCEPTABLE);
        else if (employee.getEmail().isEmpty())
            return new ResponseEntity<String>("Employee email cannot be empty.",HttpStatus.NOT_ACCEPTABLE);
        else if (employee.getPassword().isEmpty())
            return new ResponseEntity<String>("Employee password cannot be empty.",HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee,o_id), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public List<Employee> getAllEmployees()
    {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/get/ById:{e_id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable ("e_id") int e_id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getName().equals(employeeService.getEmployeeById(e_id).getEmail())  || authentication.getAuthorities().contains(new SimpleGrantedAuthority("Admin")))
        {
            try {
                return new ResponseEntity<Employee>(employeeService.getEmployeeById(e_id), HttpStatus.OK);
            } catch (EmployeeNotFoundException e) {
                return new ResponseEntity<String>("Employee does not exist.", HttpStatus.NOT_FOUND);
            }
        }
        else{
            return new ResponseEntity<String>("Unauthorized access Request.",HttpStatus.UNAUTHORIZED);
        }

    }

    @PutMapping("/update/ById:{e_id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("e_id") int e_id, @RequestBody Employee employee)
    {
        try{
            if(employee.getFirstName().isEmpty())
                return new ResponseEntity<String>("Employee first name cannot be empty.",HttpStatus.NOT_ACCEPTABLE);
            else if(employee.getLastName().isEmpty())
                return new ResponseEntity<String>("Employee last name cannot be empty.",HttpStatus.NOT_ACCEPTABLE);
            else if (employee.getSalary()<=0)
                return new ResponseEntity<String>("Employee salary cannot be empty nor zero.",HttpStatus.NOT_ACCEPTABLE);
            else if (employee.getSalary()<0)
                return new ResponseEntity<String>("Employee salary cannot be negative.",HttpStatus.NOT_ACCEPTABLE);
            else if (employee.getEmail().isEmpty())
                return new ResponseEntity<String>("Employee email cannot be empty.",HttpStatus.NOT_ACCEPTABLE);
            else if (employee.getPassword().isEmpty())
                return new ResponseEntity<String>("Employee password cannot be empty.",HttpStatus.NOT_ACCEPTABLE);


            return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,e_id),HttpStatus.OK);
        }
        catch (EmployeeNotFoundException e){
            return new ResponseEntity<String>("Employee does not exist.",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/ById:{e_id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("e_id") int e_id)
    {
        try{
            employeeService.deleteEmployee(e_id);
            return new ResponseEntity<String> ("Employee delete successfully", HttpStatus.OK);
        }
            catch (EmployeeNotFoundException e){
            return new ResponseEntity<String>("Employee does not exist.",HttpStatus.NOT_FOUND);
        }
    }
}
