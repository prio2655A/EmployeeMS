package com.exampleEMS.EmployeeMS.Repository;

import com.exampleEMS.EmployeeMS.Models.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganisationRepository extends JpaRepository<Organisation,Integer> {
}