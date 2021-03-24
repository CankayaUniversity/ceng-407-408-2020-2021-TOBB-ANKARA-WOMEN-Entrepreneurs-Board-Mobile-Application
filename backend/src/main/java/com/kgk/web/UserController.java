package com.kgk.web;

import com.kgk.model.User;
import com.kgk.repository.UserRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Body;

import javax.validation.Valid;
import java.util.Collection;

@Controller("/api/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
      this.userRepository = userRepository;
    }

    @Get
    public Collection<User> listAll() {
        return userRepository.listAllUsers();
    }

    @Get("/by-roleId/{roleId}")
    public Collection<User> listByYear(@PathVariable("roleId") String roleId) {
        return userRepository.findUsersByRoleId(roleId);
    }

    @Get("/{userId}")
    public User showUserProfile(@PathVariable("userId") String userId) {
        return userRepository.findUserByIdAndSetProfileComp(userId);
    }

    @Post
    public User save(@Valid @Body User user) {
      return userRepository.saveUser(user);
    }

    @Put("/{userId}")
    public User update(@PathVariable("userId") String userId, @Valid @Body User user) {
      return userRepository.updateUser(userId, user);
    }

    @Delete("/{userId}")
    public void delete(@PathVariable("userId") String userId){ userRepository.deleteUser(userId); }

    //Authorize for Permission Admin
    @Put("/{userId}")
    public User assignRole(@PathVariable("userId") String userId, @Valid @Body String roleId) {
      User user = userRepository.findUserById(userId);
      user.setRoleId(roleId);

      return userRepository.updateUser(userId, user);
    }

}
