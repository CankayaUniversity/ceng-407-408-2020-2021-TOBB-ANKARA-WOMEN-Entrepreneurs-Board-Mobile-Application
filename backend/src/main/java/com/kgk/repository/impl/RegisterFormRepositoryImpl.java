package com.kgk.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kgk.model.admin.RegisterForm;
import com.kgk.repository.RegisterFormRepository;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class RegisterFormRepositoryImpl implements RegisterFormRepository {

    private final DynamoDBMapper mapper;

    public RegisterFormRepositoryImpl(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public RegisterForm saveRegisterForm(RegisterForm registerForm) {
        registerForm.setRegisterId(UUID.randomUUID().toString());
        registerForm.setApproved("false");
        registerForm.setRegisterDate(System.currentTimeMillis());
        registerForm.setRoleId("103");
        mapper.save(registerForm);
        System.out.println("[REGISTER FORM REPO] Register form is saved");

        return registerForm;
    }

}
