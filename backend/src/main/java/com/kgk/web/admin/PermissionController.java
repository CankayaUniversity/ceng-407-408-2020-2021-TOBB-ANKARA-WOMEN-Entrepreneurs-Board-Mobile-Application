package com.kgk.web.admin;

import com.kgk.model.admin.RoleType;
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

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/permission")
public class PermissionController {

    private final PermissionRepository permissionRepository;

    public PermissionController(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Get
    @Secured({"PERMISSION_ADMIN", "FULL_ADMIN"})
    public List<User> listAll(Principal principal){
        return permissionRepository.listAllUserRoles(principal.getName());
    }

    @Get("/by-role-id/{roleId}")
    @Secured({"PERMISSION_ADMIN", "FULL_ADMIN"})
    public List<User> findAllUsersByRoleId(Principal principal, @PathVariable("roleId") String roleId) {
        return permissionRepository.findAllUsersByRoleId(principal.getName(), roleId);
    }

    @Get("/{userId}")
    @Secured({"PERMISSION_ADMIN", "FULL_ADMIN"})
    public User findUserRoleByUserId(@PathVariable("userId") String userId) {
        return permissionRepository.findUserRoleByUserId(userId);
    }

    @Put("/{userId}")
    @Secured({"PERMISSION_ADMIN", "FULL_ADMIN"})
    public User update(@PathVariable("userId") String userId, @Valid @Body RoleType roleType) {
        return permissionRepository.changeUserRole(userId, roleType);
    }

}
