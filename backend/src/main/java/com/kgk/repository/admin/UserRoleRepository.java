package com.kgk.repository.admin;

import com.kgk.model.admin.UserRole;

import java.util.Collection;

public interface UserRoleRepository {

    Collection<UserRole> listAllUserRoles(); //list all users with their roles

    Collection<UserRole> findAllUsersWithRoleId(String roleId); //find users with a specific role

    UserRole changeRole(UserRole userRole); //give/take role to/from user

}
