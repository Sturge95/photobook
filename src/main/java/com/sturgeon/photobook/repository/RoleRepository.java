package com.sturgeon.photobook.repository;

import com.sturgeon.photobook.bo.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findRoleByName(Role.auth_role auth_role);
}
