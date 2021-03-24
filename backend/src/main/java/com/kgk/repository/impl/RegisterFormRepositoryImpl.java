package com.kgk.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.kgk.model.RegisterForm;
import com.kgk.repository.RegisterFormRepository;

import javax.inject.Singleton;
import java.time.ZonedDateTime;

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
        registerForm.setRegisterDate(ZonedDateTime.now().toEpochSecond());

        mapper.save(registerForm);
    }

}
