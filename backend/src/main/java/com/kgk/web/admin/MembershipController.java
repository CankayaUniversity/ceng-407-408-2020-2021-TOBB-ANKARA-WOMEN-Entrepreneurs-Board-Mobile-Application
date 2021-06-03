package com.kgk.web.admin;

import com.kgk.model.admin.RegisterForm;
import com.kgk.repository.admin.MembershipRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/membership")
public class MembershipController {

    private final MembershipRepository membershipRepository;

    public MembershipController(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Get
    @Secured({"MEMBERSHIP_ADMIN", "FULL_ADMIN"})
    public List<RegisterForm> listAll(Principal principal){
        return membershipRepository.listAllUnapprovedRegisterForms(principal.getName());
    }

    @Get("/{registerId}")
    @Secured({"MEMBERSHIP_ADMIN", "FULL_ADMIN"})
    public RegisterForm findRegisterFormById(@PathVariable("registerId") String registerId){
        return membershipRepository.findRegisterFormById(registerId);
    }

    @Put("/{registerId}")
    @Secured({"MEMBERSHIP_ADMIN", "FULL_ADMIN"})
    public RegisterForm approve(@PathVariable("registerId") String registerId, @Valid @Body RegisterForm registerForm) {
        return membershipRepository.approveRegisterForm(registerId, registerForm);
    }

    @Delete("/{registerId}")
    @Secured({"MEMBERSHIP_ADMIN", "FULL_ADMIN"})
    public void decline(@PathVariable("registerId") String registerId) {
        membershipRepository.declineRegisterForm(registerId);
    }

}
