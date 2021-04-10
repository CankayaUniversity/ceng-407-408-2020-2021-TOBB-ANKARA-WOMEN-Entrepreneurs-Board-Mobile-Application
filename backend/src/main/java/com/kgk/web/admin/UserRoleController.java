package com.kgk.web.admin;

import com.kgk.model.admin.UserRole;
import com.kgk.repository.admin.UserRoleRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;

@Controller("/api/user-role")
public class UserRoleController {

    private final UserRoleRepository userRolesRepository;

    public UserRoleController(UserRoleRepository userRolesRepository) {
        this.userRolesRepository = userRolesRepository;
    }

    @Get
    public List<UserRole> listAll(){
        return userRolesRepository.listAllUserRoles();
    }

    @Get("/{roleId}")
    public List<UserRole> findAllUsersByRoleId(@PathVariable("roleId") String roleId) {
        return userRolesRepository.findAllUsersByRoleId(roleId);
    }

    @Get("/{userId}")
    public UserRole findUserRoleByUserId(@PathVariable("userId") String userId) {
        return userRolesRepository.findUserRoleByUserId(userId);
    }

    @Put("/{userId}")
    public UserRole update(@PathVariable("userId") String userId, @Valid @Body UserRole userRole) {
        return userRolesRepository.changeUserRole(userId, userRole);
    }

}
