package com.kgk.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.kgk.model.admin.RegisterForm;
import com.kgk.repository.RegisterFormRepository;

import javax.inject.Singleton;

@Singleton
public class RegisterFormRepositoryImpl implements RegisterFormRepository {

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    public RegisterFormRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config) {
        this.mapper = mapper;
        this.config = config;
    }

    @Override
    public void saveRegisterForm(RegisterForm registerForm) {
        registerForm.setApproved(false);
        registerForm.setRegisterDate(System.currentTimeMillis());
        mapper.save(registerForm);
        System.out.println("[REGISTER FORM REPO] Register form is saved");
    }

}
