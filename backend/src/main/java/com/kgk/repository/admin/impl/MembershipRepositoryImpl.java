package com.kgk.repository.admin.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kgk.model.DeletedItem;
import com.kgk.model.admin.RegisterForm;
import com.kgk.model.user.User;
import com.kgk.repository.admin.MembershipRepository;
import com.kgk.repository.user.UserRepository;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Singleton
public class MembershipRepositoryImpl implements MembershipRepository {

    private static final String TABLE_NAME = "RegisterForms";

    private static final String GSI_NAME = "registerFormsByCity";

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    private final UserRepository userRepository;

    public MembershipRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config,
                                    UserRepository userRepository) {
        this.mapper = mapper;
        this.config = config;
        this.userRepository = userRepository;
    }

    @Override
    public List<RegisterForm> listAllUnapprovedRegisterForms(String userId) {
        User currentUser = userRepository.findUserById(userId);

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":approved", new AttributeValue().withS("false"));
        eav.put(":city", new AttributeValue().withS(currentUser.getCity()));
        //eav.put(":city", new AttributeValue().withS("Ankara"));

        DynamoDBQueryExpression<RegisterForm> queryExpression = new DynamoDBQueryExpression<RegisterForm>()
                .withKeyConditionExpression("approved = :approved and city = :city")
                .withIndexName(GSI_NAME)
                .withExpressionAttributeValues(eav)
                .withConsistentRead(false);

        return mapper.query(RegisterForm.class, queryExpression);
    }

    @Override
    public RegisterForm findRegisterFormById(String registerFormId) {
        return mapper.load(RegisterForm.class, registerFormId, config);
    }

    @Override
    public RegisterForm approveRegisterForm(String registerId, RegisterForm registerForm) {
        User user = new User();

        RegisterForm retrievedForm = mapper.load(RegisterForm.class, registerId, config);
        retrievedForm.setApproved("true");

        user.setUserId(UUID.randomUUID().toString());
        user.setRoleId("MEMBER");
        user.setFirstName(retrievedForm.getFirstName());
        user.setLastName(retrievedForm.getLastName());
        user.setEmail(retrievedForm.getEmail());
        user.setPassword(retrievedForm.getPassword());
        user.setPhone(retrievedForm.getPhone());
        user.setCity(retrievedForm.getCity());
        user.setTobbRegisterId(retrievedForm.getTobbRegisterId());
        user.setOccupation(retrievedForm.getOccupation());

        mapper.save(user);
        System.out.println("[MEMBERSHIP REPO] User is saved");

        mapper.save(retrievedForm);
        System.out.println("[MEMBERSHIP REPO] Register form is updated");

        return retrievedForm;
    }

    @Override
    public void declineRegisterForm(String registerId) {
        RegisterForm retrievedForm = mapper.load(RegisterForm.class, registerId, config);
        DeletedItem deletedRegisterForm = new DeletedItem();
        deletedRegisterForm.setDeletedTime(System.currentTimeMillis());
        deletedRegisterForm.setWhichTable(TABLE_NAME);
        deletedRegisterForm.setOriginalId(retrievedForm.getRegisterId());

        try {
            //Creating the ObjectMapper object
            ObjectMapper om = new ObjectMapper();
            //Converting the Object to JSONString
            String json = om.writeValueAsString(retrievedForm);
            deletedRegisterForm.setJson(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapper.save(deletedRegisterForm);
        System.out.println("[MEMBERSHIP REPO] Deleted Register Form is saved to DeletedItems table");

        mapper.delete(retrievedForm);
        System.out.println("[MEMBERSHIP REPO] Register form is deleted");
    }

}
