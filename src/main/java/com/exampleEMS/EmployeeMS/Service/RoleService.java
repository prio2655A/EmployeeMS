package com.exampleEMS.EmployeeMS.Service;

import com.exampleEMS.EmployeeMS.Models.Role;

import java.util.List;

public interface RoleService {
    Role saveRole(Role role);
    List<Role> getAllRoles();
    Role getRoleById(int r_id);
    Role updateRole(Role role, int r_id);
    void deleteRole(int e_id);

}
