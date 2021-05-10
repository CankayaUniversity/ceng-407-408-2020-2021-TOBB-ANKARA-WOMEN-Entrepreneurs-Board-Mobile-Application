package com.kgk.web;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.kgk.model.user.Catalog;
import com.kgk.model.user.Password;
import com.kgk.model.user.User;
import com.kgk.repository.user.CatalogRepository;
import com.kgk.repository.user.UserRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Body;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Secured(SecurityRule.IS_AUTHENTICATED)
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

    // show user's own profile page
    @Get("/profile")
    public User findUsersOwnProfile(Principal principal) {
        return userRepository.findUserById(principal.getName());
    }

    //show other user's profile page
    @Get("/{userId}")
    public User findUserProfile(@PathVariable("userId") String userId) {
        return userRepository.findUserById(userId);
    }

    @Get("/{userId}/{catalogId}")
    public Catalog findCatalog(@PathVariable("userId") String userId, @PathVariable("catalogId") String catalogId) {
        return catalogRepository.findCatalogByCatalogId(userId, catalogId);
    }

    @Put("/{userId}")
    public User update(@PathVariable("userId") String userId, @Valid @Body User user) {
      return userRepository.updateUser(userId, user);
    }

    @Put("/change-password/{userId}")
    public User changePassword(@PathVariable("userId") String userId,
                               @Valid @Body Password changedPassword) {
        return userRepository.changePassword(userId, changedPassword);
    }

    @Delete("/{userId}")
    public void delete(@PathVariable("userId") String userId){ userRepository.deleteUser(userId); }

}
