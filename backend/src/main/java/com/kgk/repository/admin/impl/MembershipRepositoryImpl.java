package com.kgk.repository.admin.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kgk.model.admin.DeletedRegisterForm;
import com.kgk.model.admin.RegisterForm;
import com.kgk.model.Role;
import com.kgk.model.RoleType;
import com.kgk.model.User;
import com.kgk.model.admin.UserRole;
import com.kgk.repository.admin.MembershipRepository;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        eav.put(":approved", new AttributeValue().withBOOL(false));
        //eav.put(":city", new AttributeValue().withS(currentUser.getCity()));

        DynamoDBQueryExpression<RegisterForm> queryExpression = new DynamoDBQueryExpression<RegisterForm>()
                .withKeyConditionExpression("approved = :approved") //TODO: add city to key expression
                .withExpressionAttributeValues(eav);

        return mapper.query(RegisterForm.class, queryExpression).stream().collect(Collectors.toList());
    }

    @Override
    public void approveRegisterForm(String registerId, RegisterForm registerForm) {
        User user = new User();
        UserRole userRole = new UserRole();
        RegisterForm retrievedForm = mapper.load(RegisterForm.class, registerId, registerForm.getCity(), config);

        retrievedForm.setApproved(true);
        Role role = mapper.load(Role.class, RoleType.MEMBER.toString(), config);
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
    }

    @Override
    public void declineRegisterForm(String registerId, String city) {
        RegisterForm retrievedForm = mapper.load(RegisterForm.class, registerId, city, config);
        DeletedRegisterForm deletedRegisterForm = new DeletedRegisterForm();
        deletedRegisterForm.setFirstName(retrievedForm.getFirstName());
        deletedRegisterForm.setLastName(retrievedForm.getLastName());
        deletedRegisterForm.setEmail(retrievedForm.getEmail());
        deletedRegisterForm.setPassword(retrievedForm.getPassword());
        deletedRegisterForm.setPhone(retrievedForm.getPhone());
        deletedRegisterForm.setCity(retrievedForm.getCity());
        deletedRegisterForm.setTobbRegisterId(retrievedForm.getTobbRegisterId());
        deletedRegisterForm.setOccupation(retrievedForm.getOccupation());
        deletedRegisterForm.setRegisterDate(retrievedForm.getRegisterDate());

        mapper.save(deletedRegisterForm);
        System.out.println("[MEMBERSHIP REPO] Deleted Register Form is saved to DeletedRagisterForm table");

        mapper.delete(retrievedForm);
        System.out.println("[MEMBERSHIP REPO] Register form is deleted");
    }

}
