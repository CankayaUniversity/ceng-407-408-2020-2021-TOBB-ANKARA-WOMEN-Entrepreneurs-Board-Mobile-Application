package com.kgk.repository.admin.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kgk.model.admin.RoleType;
import com.kgk.model.user.User;
import com.kgk.repository.user.UserRepository;
import com.kgk.repository.admin.PermissionRepository;

import javax.inject.Singleton;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class PermissionRepositoryImpl implements PermissionRepository {

    private static final String CITY_GSI_NAME = "usersByCity";

    private static final String ROLEID_GSI_NAME = "usersByCityAndRoleId";

    private final DynamoDBMapper mapper;

    private final UserRepository userRepository;

    public PermissionRepositoryImpl(DynamoDBMapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> listAllUserRoles(String userId) {
        User currentUser = userRepository.findUserById(userId);

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":city", new AttributeValue().withS(currentUser.getCity()));

        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withIndexName(CITY_GSI_NAME)
                .withKeyConditionExpression("city = :city")
                .withExpressionAttributeValues(eav)
                .withConsistentRead(false);

        return mapper.query(User.class, queryExpression);
    }

    @Override
    public List<User> findAllUsersByRoleId(String userId, String roleId) {
        User currentUser = userRepository.findUserById(userId);

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":city", new AttributeValue().withS(currentUser.getCity()));
        eav.put(":roleId", new AttributeValue().withS(roleId));

        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withIndexName(ROLEID_GSI_NAME)
                .withKeyConditionExpression("city = :city and roleId = :roleId")
                .withExpressionAttributeValues(eav)
                .withConsistentRead(false);

        return mapper.query(User.class, queryExpression);
    }

    @Override
    public User findUserRoleByUserId(String userId) {
        return userRepository.findUserById(userId);
    }

    @Override
    public User changeUserRole(String userId, RoleType roleType) {
        User userRetrieved = findUserRoleByUserId(userId);
        userRetrieved.setRoleId(roleType.toString());

        mapper.save(userRetrieved);
        System.out.println("[USER ROLE REPO] User's role is changed");

        return userRetrieved;
    }

}
