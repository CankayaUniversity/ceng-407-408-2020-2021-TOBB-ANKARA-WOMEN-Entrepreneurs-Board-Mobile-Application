package com.kgk.repository.admin;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.kgk.model.admin.Role;
import com.kgk.model.user.User;

import java.util.List;

public interface PermissionRepository {

    List<User> listAllUserRoles(/*AwsProxyRequest awsRequest*/); //list all users with their roles

    List<User> findAllUsersByRoleId(/*AwsProxyRequest awsRequest, */String roleId); //find users with a specific role

    User findUserRoleByUserId(String userId); //returns a specific user role

    User changeUserRole(String userId, Role role); //changes the role of a user

}
