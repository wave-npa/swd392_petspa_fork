package org.petspa.petcaresystem.role.service.implement;

import java.util.Collection;

import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.role.model.Role;
import org.petspa.petcaresystem.role.repository.RoleRepository;
import org.petspa.petcaresystem.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Collection<Role> findAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role deleteRole(Long roleId) {
        Role newRole = roleRepository.findById(roleId).orElse(null);
        newRole.setStatus(Status.INACTIVE);
        return roleRepository.save(newRole);
    }

}
