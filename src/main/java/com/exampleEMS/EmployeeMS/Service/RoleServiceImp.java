package com.exampleEMS.EmployeeMS.Service;

import com.exampleEMS.EmployeeMS.Exception.RoleNotFoundException;
import com.exampleEMS.EmployeeMS.Models.Role;
import com.exampleEMS.EmployeeMS.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService{
    @Autowired
    private RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(int r_id) {
        return roleRepository.findById(r_id).orElseThrow( ()->new RoleNotFoundException(r_id));
    }

    @Override
    public Role updateRole(Role role, int r_id) {
        Role existingRole=roleRepository.findById(r_id).orElseThrow( ()->new RoleNotFoundException(r_id) );

        existingRole.setRoleName(role.getRoleName());

        roleRepository.save(existingRole);
        return existingRole;
    }

    @Override
    public void deleteRole(int r_id) {
        roleRepository.findById(r_id).orElseThrow( ()->new RoleNotFoundException(r_id) );
        roleRepository.deleteById(r_id);
    }
}
