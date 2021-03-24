package com.kgk.repository;

import com.kgk.model.User;
import com.kgk.model.profile.*;

import java.util.Collection;

public interface UserRepository {

  Collection<User> listAllUsers(); //list all users

  Collection<Hobby> findHobbiesByUserId(String userId);

  Collection<Competence> findCompetencesByUserId(String userId);

  Collection<Project> findProjectsByUserId(String userId);

  Collection<Language> findLanguagesByUserId(String userId);

  Collection<Certificate> findCertificatesByUserId(String userId);

  Collection<User> findUsersByRoleId(String roleId); //find users with a specific role

  User findUserByIdAndSetProfileComp(String userId);

  User findUserById(String userId); //find a specific user

  User saveUser(User user); //save user info to database

  User updateUser(String userId, User user); //update user info on database

  void deleteUser(String userId); //delete user from database

}
