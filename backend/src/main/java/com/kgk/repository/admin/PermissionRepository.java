package com.kgk.repository.admin;

import com.kgk.model.user.User;

import java.util.List;

public interface PermissionRepository {

    List<User> listAllUserRoles(); //list all users with their roles

    List<User> findAllUsersByRoleId(String roleId); //find users with a specific role

    User findUserRoleByUserId(String userId); //returns a specific user role

    User changeUserRole(String userId, User user); //changes the role of a user

}
