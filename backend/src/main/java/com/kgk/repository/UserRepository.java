package com.kgk.repository;

import com.kgk.model.User;

import java.util.Collection;

public interface UserRepository {

  Collection<User> listAllUsers(); //list all users

  Collection<User> findUsersByRoleId(String roleId); //find users with a specific role

  User findUserById(String userId); //find a specific user

  User saveUser(User user); //save user info to database

  User updateUser(String userId, User user); //update user info on database

  User changePassword(String userId, String oldPassword, String newPassword); //change password

  void deleteUser(String userId); //delete user from database

}
