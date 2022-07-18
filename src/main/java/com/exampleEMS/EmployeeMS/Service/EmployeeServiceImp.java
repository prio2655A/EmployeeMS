package com.exampleEMS.EmployeeMS.Service;

import com.exampleEMS.EmployeeMS.Exception.EmployeeNotFoundException;
import com.exampleEMS.EmployeeMS.Exception.OrganisationNotFoundException;
import com.exampleEMS.EmployeeMS.Models.Employee;
import com.exampleEMS.EmployeeMS.Models.Organisation;
import com.exampleEMS.EmployeeMS.Repository.EmployeeRepository;
import com.exampleEMS.EmployeeMS.Repository.OrganisationRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class EmployeeServiceImp implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

//    @Override
//    public Employee saveEmployee(Employee employee) {
//        return employeeRepository.save(employee);
//    }

    //
    @Autowired
    private OrganisationRepository organisationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmployeeServiceImp(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee, int o_id) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        Organisation createOrganisation= organisationRepository.findById(o_id).orElseThrow( ()->new OrganisationNotFoundException(o_id) );
        employee.setOrganisation(createOrganisation);
        return employeeRepository.save(employee) ;
    }
    //

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int e_id) {
        return employeeRepository.findById(e_id).orElseThrow( ()->new EmployeeNotFoundException(e_id) );
    }

    @Override
    public Employee updateEmployee(Employee employee, int e_id) {
        Employee existingEmployee=employeeRepository.findById(e_id).orElseThrow( ()->new EmployeeNotFoundException(e_id) );

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPassword(passwordEncoder.encode(employee.getPassword()));
        existingEmployee.setRoles(employee.getRoles());

        employeeRepository.save(existingEmployee);

        return existingEmployee;
    }

    @Override
    public void deleteEmployee(int e_id)
    {
        employeeRepository.findById(e_id).orElseThrow( ()->new EmployeeNotFoundException(e_id) );
        employeeRepository.deleteById(e_id);
    }
}
