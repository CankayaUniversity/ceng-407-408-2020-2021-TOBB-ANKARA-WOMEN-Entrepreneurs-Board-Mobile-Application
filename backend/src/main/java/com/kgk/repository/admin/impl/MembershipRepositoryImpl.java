package com.kgk.repository.admin.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kgk.model.RegisterForm;
import com.kgk.model.User;
import com.kgk.model.profile.Competence;
import com.kgk.repository.admin.MembershipRepository;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class MembershipRepositoryImpl implements MembershipRepository {

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    public MembershipRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config) {
        this.mapper = mapper;
        this.config = config;
    }

    @Override
    public Collection<RegisterForm> listAllUnapprovedRegisterForms() {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":approved", new AttributeValue().withBOOL(false));

        DynamoDBQueryExpression<RegisterForm> queryExpression = new DynamoDBQueryExpression<RegisterForm>()
                .withKeyConditionExpression("approved = :approved")
                .withExpressionAttributeValues(eav);

        return mapper.query(RegisterForm.class, queryExpression);
    }

    @Override
    public void approveRegisterForm(String registerId) {
        User user = new User();
        RegisterForm registerForm = mapper.load(RegisterForm.class, registerId, config);
        user.setRoleId("101");
        user.setFirstName(registerForm.getFirstName());
        user.setLastName(registerForm.getLastName());
        user.setEmail(registerForm.getEmail());
        user.setPhone(registerForm.getPhone());

        mapper.save(user);
    }

    @Override
    public void declineRegisterForm(String registerId) {
        RegisterForm registerForm = mapper.load(RegisterForm.class, registerId, config);

        mapper.delete(registerForm);
    }

}