package com.kgk.web.admin;

import com.kgk.model.admin.UserRole;
import com.kgk.repository.admin.UserRoleRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.PathVariable;

import javax.validation.Valid;
import java.util.Collection;

@Controller("/api/user-role")
public class UserRoleController {

    private final UserRoleRepository userRolesRepository;

    public UserRoleController(UserRoleRepository userRolesRepository) {
        this.userRolesRepository = userRolesRepository;
    }

    @Get
    public Collection<UserRole> listAll(){
        return userRolesRepository.listAllUserRoles();
    }

    @Get("/{roleId}")
    public Collection<UserRole> findUserRolesWithUserId(@PathVariable("roleId") String roleId) {
        return userRolesRepository.findAllUsersWithRoleId(roleId);
    }

    @Put
    public UserRole update(@Valid @Body UserRole userRole) {
        return userRolesRepository.changeRole(userRole);
    }

}
