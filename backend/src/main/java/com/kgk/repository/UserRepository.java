package com.kgk.repository;

import com.kgk.model.User;
import com.kgk.model.profile.*;

import java.util.Collection;

public interface UserRepository {

  Collection<User> listAllUsers();

  Collection<Hobby> findHobbiesByUserId(String userId);

  Collection<Competence> findCompetencesByUserId(String userId);

  Collection<Project> findProjectsByUserId(String userId);

  Collection<Language> findLanguagesByUserId(String userId);

  Collection<Certificate> findCertificatesByUserId(String userId);

  Collection<User> findUsersByRoleId(String roleId);

  User findUserByIdAndSetProfileComp(String userId);

  User findUserById(String userId);

  User saveUser(User user);

  User updateUser(String userId, User user);

  void deleteUser(String userId);

}
