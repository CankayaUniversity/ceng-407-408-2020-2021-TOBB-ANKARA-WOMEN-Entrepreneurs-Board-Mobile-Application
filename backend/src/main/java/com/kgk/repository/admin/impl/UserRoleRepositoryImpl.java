package com.kgk.repository.admin.impl;

import com.kgk.model.admin.UserRole;
import com.kgk.repository.admin.UserRoleRepository;

import javax.inject.Singleton;
import java.util.Collection;

@Singleton
public class UserRoleRepositoryImpl implements UserRoleRepository {
    //TODO: implement methods
    @Override
    public Collection<UserRole> listAllUserRoles() {
        return null;
    }

    @Override
    public Collection<UserRole> findAllUsersWithRoleId(String roleId) {
        return null;
    }

    @Override
    public UserRole saveUserRole(UserRole userRole) {
        return null;
    }

    @Override
    public UserRole updateUserRole(UserRole userRole) {
        return null;
    }

    @Override
    public void deleteRole(String roleId) {

    }

}
