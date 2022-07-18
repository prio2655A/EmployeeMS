package com.exampleEMS.EmployeeMS.Service;

import com.exampleEMS.EmployeeMS.Exception.OrganisationNotFoundException;
import com.exampleEMS.EmployeeMS.Models.Organisation;
import com.exampleEMS.EmployeeMS.Repository.OrganisationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationServiceImp implements OrganisationService{
    private OrganisationRepository organisationRepository;

    public OrganisationServiceImp(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    @Override
    public Organisation saveOrganisation(Organisation organisation) {
        return organisationRepository.save(organisation);
    }

    @Override
    public List<Organisation> getAllOrganisations() {
        return organisationRepository.findAll();
    }

    @Override
    public Organisation getOrganisationById(int o_id) {
        return organisationRepository.findById(o_id).orElseThrow( ()->new OrganisationNotFoundException(o_id) );
    }

    @Override
    public Organisation updateOrganisation(Organisation organisation, int o_id) {
        Organisation existingOrganisation=organisationRepository.findById(o_id).orElseThrow( ()->new OrganisationNotFoundException(o_id) );

        existingOrganisation.setOrganisationName(organisation.getOrganisationName());
        existingOrganisation.setAddress(organisation.getAddress());

        organisationRepository.save(existingOrganisation);
        return existingOrganisation;
    }

    @Override
    public void deleteOrganisation(int o_id)
    {
        organisationRepository.findById(o_id).orElseThrow( ()->new OrganisationNotFoundException(o_id) );
        organisationRepository.deleteById(o_id);
    }
}
