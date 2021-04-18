package com.kgk.repository.user;

import com.kgk.model.user.Password;
import com.kgk.model.user.User;

import java.util.List;

public interface UserRepository {

    List<User> listAllUsers(); //list all users

    User findUserById(String userId); //find a specific user

    User updateUser(String userId, User user); //update user info on database

    User changePassword(String userId, Password changedPassword); //change password

    void deleteUser(String userId); //delete user from database

}
