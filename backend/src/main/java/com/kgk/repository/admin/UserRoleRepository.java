package com.kgk.repository.admin;

import com.kgk.model.admin.UserRole;

import java.util.List;

public interface UserRoleRepository {

    List<UserRole> listAllUserRoles(); //list all users with their roles

    List<UserRole> findAllUsersWithRoleId(String roleId, String city); //find users with a specific role

    UserRole changeRole(UserRole userRole); //give/take role to/from user

}
