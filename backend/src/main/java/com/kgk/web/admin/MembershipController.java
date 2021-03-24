package com.kgk.web.admin;

import com.kgk.model.RegisterForm;
import com.kgk.repository.admin.MembershipRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.PathVariable;


import java.util.Collection;

@Controller("/api/membership")
public class MembershipController {

    private final MembershipRepository membershipRepository;

    public MembershipController(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Get
    public Collection<RegisterForm> listAll(){
        return membershipRepository.listAllUnapprovedRegisterForms();
    }

    @Put("/{registerId}")
    public void approve(@PathVariable("registerId") String registerId) {
        membershipRepository.approveRegisterForm(registerId);
    }

    @Delete("/{registerId}")
    public void decline(@PathVariable("registerId") String registerId) {
        membershipRepository.declineRegisterForm(registerId);
    }
}
