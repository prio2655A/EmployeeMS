package com.exampleEMS.EmployeeMS.Service;

import com.exampleEMS.EmployeeMS.Models.Organisation;

import java.util.List;

public interface OrganisationService {
    Organisation saveOrganisation(Organisation organisation);
    List<Organisation> getAllOrganisations();
    Organisation getOrganisationById(int o_id);
    Organisation updateOrganisation(Organisation organisation, int o_id);
    void deleteOrganisation(int o_id);

}
