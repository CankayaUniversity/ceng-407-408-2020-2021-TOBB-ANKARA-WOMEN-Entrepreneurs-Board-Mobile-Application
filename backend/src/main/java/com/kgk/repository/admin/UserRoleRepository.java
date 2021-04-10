package com.kgk.repository.admin;

import com.kgk.model.admin.UserRole;

import java.util.List;

public interface UserRoleRepository {

    List<UserRole> listAllUserRoles(); //list all users with their roles

    List<UserRole> findAllUsersByRoleId(String roleId); //find users with a specific role

    UserRole findUserRoleByUserId(String userId); //returns a specific user role

    UserRole changeUserRole(String userId, UserRole userRole); //changes the role of a user

    void deleteUserRole(String userId);

}
