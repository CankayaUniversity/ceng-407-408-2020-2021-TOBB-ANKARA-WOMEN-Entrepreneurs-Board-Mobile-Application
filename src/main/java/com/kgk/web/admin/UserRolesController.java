package com.kgk.web.admin;

import com.kgk.model.User;
import com.kgk.model.admin.UserRole;
import com.kgk.repository.admin.UserRolesRepository;
import io.micronaut.http.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller("user-role")
public class UserRolesController {

    private final UserRolesRepository userRolesRepository;

    public UserRolesController(UserRolesRepository userRolesRepository) {
        this.userRolesRepository = userRolesRepository;
    }

    @Post
    public UserRole save(@Valid @Body UserRole userRole) {
        return userRolesRepository.saveUserRole(userRole);
    }

    @Put
    public UserRole update(@Valid @Body UserRole userRole) {
        return userRolesRepository.updateUserRole(userRole);
    }

    @Get
    public Collection<UserRole> listAll(){
        return userRolesRepository.listAllUserRoles();
    }

    @Get("/{roleId}")
    public Collection<UserRole> findUserRolesWithUserId(@PathVariable("roleId") String roleId) {
        return userRolesRepository.findAllUsersWithRoleId(roleId);
    }

    @Delete
    public void delete(@Valid @Body UserRole userRole) {
        userRolesRepository.deleteRole(userRole);
    }

}
