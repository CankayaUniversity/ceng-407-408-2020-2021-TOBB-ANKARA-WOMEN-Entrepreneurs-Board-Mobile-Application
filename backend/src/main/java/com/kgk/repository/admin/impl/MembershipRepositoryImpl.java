package com.kgk.repository.admin.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kgk.model.RegisterForm;
import com.kgk.model.User;
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
        //TODO: CurrentUser's city info must pulled
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":approved", new AttributeValue().withBOOL(false));
        //eav.put(":city", new AttributeValue().withS(currentUser.getCity()));

        DynamoDBQueryExpression<RegisterForm> queryExpression = new DynamoDBQueryExpression<RegisterForm>()
                .withKeyConditionExpression("approved = :approved") //TODO: add city to key expression
                .withExpressionAttributeValues(eav);

        return mapper.query(RegisterForm.class, queryExpression);
    }

    @Override
    public void approveRegisterForm(String registerId, RegisterForm registerForm) {
        User user = new User();
        RegisterForm retrievedForm = mapper.load(RegisterForm.class, registerId, registerForm.getCity(), config);

        retrievedForm.setApproved(true);
        user.setRoleId("101");
        user.setFirstName(retrievedForm.getFirstName());
        user.setLastName(retrievedForm.getLastName());
        user.setEmail(retrievedForm.getEmail());
        user.setPassword(retrievedForm.getPassword());
        user.setPhone(retrievedForm.getPhone());
        user.setCity(retrievedForm.getCity());
        user.setTobbRegisterId(retrievedForm.getTobbRegisterId());

        mapper.save(retrievedForm);
        System.out.println("[MEMBERSHIP REPO] Register form is updated");
        mapper.save(user);
        System.out.println("[MEMBERSHIP REPO] User is saved");
    }

    @Override
    public void declineRegisterForm(String registerId, String city) {
        RegisterForm retrievedForm = mapper.load(RegisterForm.class, registerId, city, config);
        mapper.delete(retrievedForm);
        System.out.println("[MEMBERSHIP REPO] Register form is deleted");
    }

}
