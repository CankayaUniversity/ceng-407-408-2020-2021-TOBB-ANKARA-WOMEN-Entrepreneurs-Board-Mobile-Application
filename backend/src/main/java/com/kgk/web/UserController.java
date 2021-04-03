package com.kgk.web;

import com.kgk.model.Catalog;
import com.kgk.model.User;
import com.kgk.repository.CatalogRepository;
import com.kgk.repository.UserRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Body;

import javax.validation.Valid;
import java.util.List;

@Controller("/api/user")
public class UserController {

    private final UserRepository userRepository;

    private final CatalogRepository catalogRepository;

    public UserController(UserRepository userRepository, CatalogRepository catalogRepository) {
        this.userRepository = userRepository;
        this.catalogRepository = catalogRepository;
    }

    @Get
    public List<User> listAll() {
        return userRepository.listAllUsers();
    }

    @Get("/{userId}")
    public User showUserProfile(@PathVariable("userId") String userId) {
        return userRepository.findUserById(userId);
    }

    @Get("/{userId}/{catalogId}")
    public Catalog showCatalog(@PathVariable("userId") String userId, @PathVariable("catalogId") String catalogId) {
        return catalogRepository.findCatalogByCatalogId(userId, catalogId);
    }

    /*@Post
    public User save(@Valid @Body User user) {
      return userRepository.saveUser(user);
    }*/

    @Put("/{userId}")
    public User update(@PathVariable("userId") String userId, @Valid @Body User user) {
      return userRepository.updateUser(userId, user);
    }

    @Put("/change-password/{userId}")
    //TODO: tek body gönderilmeli, direk update metoduna mı dahil olsa
    public User changePassword(@PathVariable("userId") String userId, @Valid @Body String oldPassword,
                               @Valid @Body String newPassword) {
        return userRepository.changePassword(userId, oldPassword, newPassword);
    }

    @Delete("/{userId}")
    public void delete(@PathVariable("userId") String userId){ userRepository.deleteUser(userId); }

    /*@Delete("/{userId}/{catalogId}")
    public void deleteCatalog(@PathVariable("userId") String userId, @PathVariable("catalogId") String catalogId) {
        catalogRepository.deleteCatalog(userId, catalogId);
    }*/

    //Authorize for Permission Admin
    /*@Put("/{userId}")
    public User assignRole(@PathVariable("userId") String userId, @Valid @Body String roleId) {
      User user = userRepository.findUserById(userId);
      user.setRoleId(roleId);

      return userRepository.updateUser(userId, user);
    }*/

}
