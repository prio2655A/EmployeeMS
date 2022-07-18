package com.exampleEMS.EmployeeMS.Controllers;

import com.exampleEMS.EmployeeMS.Exception.OrganisationNotFoundException;
import com.exampleEMS.EmployeeMS.Models.Organisation;
import com.exampleEMS.EmployeeMS.Service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/EMS/organisation")
public class OrganisationController {
    @Autowired
    private OrganisationService organisationService;

    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }
    @PostMapping("/add")
    public ResponseEntity<?> saveOrganisation(@RequestBody Organisation organisation) {
        if (organisation.getOrganisationName().isEmpty())
            return new ResponseEntity<String>("Organisation name cannot be empty.",HttpStatus.NOT_ACCEPTABLE);
        else if (organisation.getAddress().isEmpty())
            return new ResponseEntity<String>("Organisation address cannot be empty.",HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<Organisation>(organisationService.saveOrganisation(organisation),HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public List<Organisation> getAllOrganisations() {
            return organisationService.getAllOrganisations();
    }

    @GetMapping("/get/ById:{o_id}")
    public ResponseEntity<?> getOrganisationById(@PathVariable ("o_id") int o_id)
    {
        try{
            return new ResponseEntity<Organisation>(organisationService.getOrganisationById(o_id), HttpStatus.OK);
        }
        catch (OrganisationNotFoundException e){
            return new ResponseEntity<String>("Organisation does not exist.",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/ById:{o_id}")
    public ResponseEntity<?> updateOrganisation(@PathVariable("o_id") int o_id, @RequestBody Organisation organisation)
    {
        try{
            if (organisation.getOrganisationName().isEmpty())
                return new ResponseEntity<String>("Organisation name cannot be empty.",HttpStatus.NOT_ACCEPTABLE);
            else if (organisation.getAddress().isEmpty())
                return new ResponseEntity<String>("Organisation address cannot be empty.",HttpStatus.NOT_ACCEPTABLE);


            return new ResponseEntity<Organisation>(organisationService.updateOrganisation(organisation,o_id),HttpStatus.OK);
        }
        catch (OrganisationNotFoundException e){
            return new ResponseEntity<String>("Organisation does not exist.",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/ById:{o_id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("o_id") int o_id)
    {
        try{
            organisationService.deleteOrganisation(o_id);
            return new ResponseEntity<String> ("Organisation delete successfully", HttpStatus.OK);
        }
        catch (OrganisationNotFoundException e){
            return new ResponseEntity<String>("Organisation does not exist.",HttpStatus.NOT_FOUND);
        }
    }

}
