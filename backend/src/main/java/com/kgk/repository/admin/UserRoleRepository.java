package com.kgk.repository.admin;

import com.kgk.model.admin.UserRole;

import java.util.Collection;

public interface UserRoleRepository {

    Collection<UserRole> listAllUserRoles();

    Collection<UserRole> findAllUsersWithRoleId(String roleId);

    UserRole saveUserRole(UserRole userRole);

    UserRole updateUserRole(UserRole userRole);

    void deleteRole(String roleId);
}
