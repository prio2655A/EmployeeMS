package com.exampleEMS.EmployeeMS.Controllers;

import com.exampleEMS.EmployeeMS.Exception.RoleNotFoundException;
import com.exampleEMS.EmployeeMS.Models.Role;
import com.exampleEMS.EmployeeMS.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/EMS/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveRole(@RequestBody Role role) {
        if (role.getRoleName().isEmpty())
            return new ResponseEntity<String>("Role name cannot be empty.",HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<Role>(roleService.saveRole(role),HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public List<Role> getAllRoles()
    {
        return roleService.getAllRoles();
    }

    @GetMapping("/get/ById:{r_id}")
    public ResponseEntity<?> getRoleById(@PathVariable("r_id") int r_id)
    {
        try{
            return new ResponseEntity<Role>(roleService.getRoleById(r_id), HttpStatus.OK);
        }
        catch (RoleNotFoundException e){
            return new ResponseEntity<String>("Role does not exist.",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/ById:{r_id}")
    public ResponseEntity<?> updateRole(@PathVariable("r_id") int r_id, @RequestBody Role role)
    {
        try{
            if (role.getRoleName().isEmpty())
                return new ResponseEntity<String>("Role name cannot be empty.",HttpStatus.NOT_ACCEPTABLE);

            return new ResponseEntity<Role>(roleService.updateRole(role,r_id),HttpStatus.OK);
        }
        catch (RoleNotFoundException e){
            return new ResponseEntity<String>("Role does not exist.",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/ById:{r_id}")
    public ResponseEntity<String> deleteRole(@PathVariable("r_id") int r_id)
    {
        try{
            roleService.deleteRole(r_id);
            return new ResponseEntity<String> ("Role delete successfully", HttpStatus.OK);
        }
        catch (RoleNotFoundException e){
            return new ResponseEntity<String>("Role does not exist.",HttpStatus.NOT_FOUND);
        }
    }


}
