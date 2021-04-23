package com.kgk.web.admin;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
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

    //TODO: how to send a AwsProxyRequest
    @Get
    public List<RegisterForm> listAll(AwsProxyRequest awsRequest){
        return membershipRepository.listAllUnapprovedRegisterForms(awsRequest);
    }

    @Get("/{registerId}")
    public RegisterForm findRegisterFormById(@PathVariable("registerId") String registerId){
        return membershipRepository.findRegisterFormById(registerId);
    }

    @Put("/{registerId}")
    public RegisterForm approve(@PathVariable("registerId") String registerId, @Valid @Body RegisterForm registerForm) {
        return membershipRepository.approveRegisterForm(registerId, registerForm);
    }

    @Delete("/{registerId}")
    public void decline(@PathVariable("registerId") String registerId) {
        membershipRepository.declineRegisterForm(registerId);
    }

}
