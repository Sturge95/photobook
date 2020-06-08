package com.sturgeon.photobook.service.impl;

import com.sturgeon.photobook.bo.Role;
import com.sturgeon.photobook.repository.RoleRepository;
import com.sturgeon.photobook.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRole(Role.auth_role auth_role) {
        return roleRepository.findRoleByName(auth_role);
    }
}
