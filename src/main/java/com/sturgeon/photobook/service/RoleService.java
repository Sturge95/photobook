package com.sturgeon.photobook.service;

import com.sturgeon.photobook.bo.Role;

public interface RoleService {
    Role getRole(Role.auth_role auth_role);
}
