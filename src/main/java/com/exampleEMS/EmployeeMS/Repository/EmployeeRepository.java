package com.exampleEMS.EmployeeMS.Repository;

import com.exampleEMS.EmployeeMS.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Optional<Employee> findByEmail(String email);

}