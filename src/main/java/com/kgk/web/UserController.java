package com.kgk.web;

import com.kgk.model.User;
import com.kgk.repository.UserRepository;
import io.micronaut.http.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
      this.userRepository = userRepository;
    }

    @Post
    public User save(@Valid @Body User user) {
      return userRepository.saveUser(user);
    }

    @Put("/{userId}")
    public User update(@PathVariable("userId") String userId, @Valid @Body User user) {
      return userRepository.updateUser(userId, user);
    }

    @Delete
    public void delete(@Valid @Body User user){ userRepository.deleteUser(user); }

    @Get
    public Collection<User> listAll() {
      return userRepository.listAllUsers();
    }

    @Get("/by-roleId/{roleId}")
    public Collection<User> listByYear(@PathVariable("roleId") String roleId) {
      return userRepository.findUsersByRoleId(roleId);
    }

    //Authorize for Permission Admin
    @Put("/{userId}")
    public User assignRole(@PathVariable("userId") String userId, @Valid @Body String roleId) {
      User user = userRepository.findUserById(userId);
      user.setRoleId(roleId);

      return userRepository.updateUser(userId, user);
    }

}
