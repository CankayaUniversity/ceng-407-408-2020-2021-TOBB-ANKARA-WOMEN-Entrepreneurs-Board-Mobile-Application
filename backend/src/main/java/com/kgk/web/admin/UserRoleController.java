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

    @Get("/{roleId}/{city}")
    public List<UserRole> findUserRolesWithUserId(@PathVariable("roleId") String roleId, @PathVariable("city") String city) {
        return userRolesRepository.findAllUsersWithRoleId(roleId, city);
    }

    @Put
    public UserRole update(@Valid @Body UserRole userRole) {
        return userRolesRepository.changeRole(userRole);
    }

}
