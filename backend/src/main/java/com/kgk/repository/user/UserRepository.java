package com.kgk.repository.user;

import com.kgk.model.user.Password;
import com.kgk.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> listAllUsers(); //list all users

    User findUserById(String userId); //find a specific user

    Optional<User> findUserByEmail(String email); //find a specific user

    User updateUser(String userId, User user); //update user info on database

    User changePassword(String userId, Password changedPassword); //change password

    void deleteUser(String userId); //delete user from database

}
