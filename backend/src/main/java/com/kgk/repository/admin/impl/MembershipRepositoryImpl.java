package com.kgk.repository.admin.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kgk.model.DeletedItem;
import com.kgk.model.admin.RegisterForm;
import com.kgk.model.admin.Role;
import com.kgk.model.admin.RoleType;
import com.kgk.model.user.User;
import com.kgk.model.admin.UserRole;
import com.kgk.repository.admin.MembershipRepository;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Singleton
public class MembershipRepositoryImpl implements MembershipRepository {

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    public MembershipRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config) {
        this.mapper = mapper;
        this.config = config;
    }

    @Override
    public List<RegisterForm> listAllUnapprovedRegisterForms() {
        //TODO: CurrentUser's city info must pulled
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":approved", new AttributeValue().withS("false"));
        //eav.put(":city", new AttributeValue().withS(currentUser.getCity()));
        eav.put(":city", new AttributeValue().withS("Ankara"));

        DynamoDBQueryExpression<RegisterForm> queryExpression = new DynamoDBQueryExpression<RegisterForm>()
                .withKeyConditionExpression("approved = :approved and city = :city")
                .withIndexName("registerFormsByCity")
                .withExpressionAttributeValues(eav)
                .withConsistentRead(false);

        return mapper.query(RegisterForm.class, queryExpression);
    }

    @Override
    public RegisterForm findRegisterFormById(String registerFormId) {
        RegisterForm registerForm = mapper.load(RegisterForm.class, registerFormId, config);
        return registerForm;
    }

    @Override
    public RegisterForm approveRegisterForm(String registerId, RegisterForm registerForm) {
        User user = new User();
        UserRole userRole = new UserRole();
        RegisterForm retrievedForm = mapper.load(RegisterForm.class, registerId, config);

        retrievedForm.setApproved("true");
        Role role = mapper.load(Role.class, RoleType.MEMBER.toString(), "101", config);
        user.setUserId(UUID.randomUUID().toString());
        user.setRoleId(role.getRoleId());
        user.setFirstName(retrievedForm.getFirstName());
        user.setLastName(retrievedForm.getLastName());
        user.setEmail(retrievedForm.getEmail());
        user.setPassword(retrievedForm.getPassword());
        user.setPhone(retrievedForm.getPhone());
        user.setCity(retrievedForm.getCity());
        user.setTobbRegisterId(retrievedForm.getTobbRegisterId());
        user.setOccupation(retrievedForm.getOccupation());
        mapper.save(retrievedForm);

        userRole.setRoleId(user.getRoleId());
        userRole.setUserId(user.getUserId());
        userRole.setCity(user.getCity());
        mapper.save(userRole);

        System.out.println("[MEMBERSHIP REPO] Register form is updated");
        mapper.save(user);
        System.out.println("[MEMBERSHIP REPO] User is saved");
        return retrievedForm;
    }

    @Override
    public void declineRegisterForm(String registerId, String city) {
        RegisterForm retrievedForm = mapper.load(RegisterForm.class, registerId, city, config);
        DeletedItem deletedItem = new DeletedItem();
        deletedItem.setDeletedTime(System.currentTimeMillis());
        deletedItem.setWhichTable("RegisterForms");
        deletedItem.setOriginalId(retrievedForm.getRegisterId());

        try {
            //Creating the ObjectMapper object
            ObjectMapper om = new ObjectMapper();
            //Converting the Object to JSONString
            String json = om.writeValueAsString(retrievedForm);
            deletedItem.setJson(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapper.save(deletedItem);
        System.out.println("[MEMBERSHIP REPO] Deleted Register Form is saved to DeletedItems table");

        mapper.delete(retrievedForm);
        System.out.println("[MEMBERSHIP REPO] Register form is deleted");
    }

}
