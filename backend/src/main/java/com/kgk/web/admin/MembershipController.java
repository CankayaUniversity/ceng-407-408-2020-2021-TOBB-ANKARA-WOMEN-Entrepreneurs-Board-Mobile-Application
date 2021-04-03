package com.kgk.web.admin;

import com.kgk.model.admin.RegisterForm;
import com.kgk.repository.admin.MembershipRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;

@Controller("/api/membership")
public class MembershipController {

    private final MembershipRepository membershipRepository;

    public MembershipController(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Get
    public List<RegisterForm> listAll(){
        return membershipRepository.listAllUnapprovedRegisterForms();
    }

    @Put("/{registerId}")
    public void approve(@PathVariable("registerId") String registerId, @Valid @Body RegisterForm registerForm) {
        membershipRepository.approveRegisterForm(registerId, registerForm);
    }

    @Delete("/{registerId}/{city}")
    public void decline(@PathVariable("registerId") String registerId, @PathVariable("city") String city) {
        membershipRepository.declineRegisterForm(registerId, city);
    }
}
