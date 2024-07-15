package org.petspa.petcaresystem.role.service;

import java.util.Collection;

import org.petspa.petcaresystem.role.model.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    public Collection<Role> findAllRole();

    public Role findRoleById(Long roleId);

    public Role saveRole(Role role);

    public Role updateRole(Role role);

    public Role deleteRole(Long roleId);


}
