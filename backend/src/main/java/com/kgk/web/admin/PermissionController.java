package com.kgk.web.admin;

import com.kgk.model.admin.Role;
import com.kgk.model.user.User;
import com.kgk.repository.admin.PermissionRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/api/permission")
public class PermissionController {

    private final PermissionRepository userRolesRepository;

    public PermissionController(PermissionRepository userRolesRepository) {
        this.userRolesRepository = userRolesRepository;
    }

    @Get
    public List<User> listAll(Principal principal){
        return userRolesRepository.listAllUserRoles(principal.getName());
    }

    @Get("/by-role-id/{roleId}")
    public List<User> findAllUsersByRoleId(Principal principal, @PathVariable("roleId") String roleId) {
        return userRolesRepository.findAllUsersByRoleId(principal.getName(), roleId);
    }

    @Get("/{userId}")
    public User findUserRoleByUserId(@PathVariable("userId") String userId) {
        return userRolesRepository.findUserRoleByUserId(userId);
    }

    @Put("/{userId}")
    public User update(@PathVariable("userId") String userId, @Valid @Body Role role) {
        return userRolesRepository.changeUserRole(userId, role);
    }

}
