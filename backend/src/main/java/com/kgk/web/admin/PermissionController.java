package com.kgk.web.admin;

import com.kgk.model.user.User;
import com.kgk.repository.admin.PermissionRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;

@Controller("/api/permission")
public class PermissionController {

    private final PermissionRepository userRolesRepository;

    public PermissionController(PermissionRepository userRolesRepository) {
        this.userRolesRepository = userRolesRepository;
    }

    @Get
    public List<User> listAll(){
        return userRolesRepository.listAllUserRoles();
    }

    @Get("/by-role-id/{roleId}")
    public List<User> findAllUsersByRoleId(@PathVariable("roleId") String roleId) {
        return userRolesRepository.findAllUsersByRoleId(roleId);
    }

    @Get("/{userId}")
    public User findUserRoleByUserId(@PathVariable("userId") String userId) {
        return userRolesRepository.findUserRoleByUserId(userId);
    }

    @Put("/{userId}")
    public User update(@PathVariable("userId") String userId, @Valid @Body User user) {
        return userRolesRepository.changeUserRole(userId, user);
    }

}
