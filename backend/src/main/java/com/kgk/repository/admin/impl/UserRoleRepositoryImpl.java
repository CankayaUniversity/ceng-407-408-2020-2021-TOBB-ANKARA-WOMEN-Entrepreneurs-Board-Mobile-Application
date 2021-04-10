package com.kgk.repository.admin.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kgk.model.user.User;
import com.kgk.model.admin.UserRole;
import com.kgk.repository.user.UserRepository;
import com.kgk.repository.admin.UserRoleRepository;

import javax.inject.Singleton;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class UserRoleRepositoryImpl implements UserRoleRepository {

    private static final String CITY_GSI_NAME = "userRolesByCity";

    private static final String ROLEID_GSI_NAME = "userRolesByCityAndRoleId";

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    private final UserRepository userRepository;

    public UserRoleRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config, UserRepository userRepository) {
        this.mapper = mapper;
        this.config = config;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserRole> listAllUserRoles() {
        Map<String, AttributeValue> eav = new HashMap<>();
        //eav.put(":city", new AttributeValue().withS(currentUser.getCity()));
        eav.put(":city", new AttributeValue().withS("Ankara"));

        DynamoDBQueryExpression<UserRole> queryExpression = new DynamoDBQueryExpression<UserRole>()
                .withIndexName(CITY_GSI_NAME)
                .withKeyConditionExpression("city = :city")
                .withExpressionAttributeValues(eav)
                .withConsistentRead(false);

        return mapper.query(UserRole.class, queryExpression);
    }

    @Override
    public List<UserRole> findAllUsersByRoleId(String roleId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        //eav.put(":city", new AttributeValue().withS(currentUser.getCity()));
        eav.put(":city", new AttributeValue().withS("Ankara"));
        eav.put(":roleId", new AttributeValue().withS(roleId));

        DynamoDBQueryExpression<UserRole> queryExpression = new DynamoDBQueryExpression<UserRole>()
                .withIndexName(ROLEID_GSI_NAME)
                .withKeyConditionExpression("city = :city and roleId = :roleId")
                .withExpressionAttributeValues(eav)
                .withConsistentRead(false);

        return mapper.query(UserRole.class, queryExpression);
    }

    @Override
    public UserRole findUserRoleByUserId(String userId) {
        return mapper.load(UserRole.class, userId, config);
    }

    @Override
    public UserRole changeRole(String userId, UserRole userRole) {
        User user = userRepository.findUserById(userId);
        user.setRoleId(userRole.getRoleId());
        mapper.save(user);
        System.out.println("[USER ROLE REPO] User's role is changed");

        mapper.save(userRole);
        System.out.println("[USER ROLE REPO] User role is saved");

        return userRole;
    }

}
