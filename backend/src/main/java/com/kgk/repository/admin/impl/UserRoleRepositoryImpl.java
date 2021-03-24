package com.kgk.repository.admin.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kgk.model.User;
import com.kgk.model.admin.UserRole;
import com.kgk.repository.UserRepository;
import com.kgk.repository.admin.UserRoleRepository;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class UserRoleRepositoryImpl implements UserRoleRepository {

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    private final UserRepository userRepository;

    public UserRoleRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config, UserRepository userRepository) {
        this.mapper = mapper;
        this.config = config;
        this.userRepository = userRepository;
    }

    @Override
    public Collection<UserRole> listAllUserRoles() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        Collection<UserRole> userRoles = mapper.scan(UserRole.class, scanExpression);

        return userRoles;
    }

    @Override
    public Collection<UserRole> findAllUsersWithRoleId(String roleId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":roleId", new AttributeValue().withS(roleId));

        DynamoDBQueryExpression<UserRole> queryExpression = new DynamoDBQueryExpression<UserRole>()
                .withKeyConditionExpression("roleId = :roleId")
                .withExpressionAttributeValues(eav);

        return mapper.query(UserRole.class, queryExpression);

    }

    @Override
    public UserRole changeRole(UserRole userRole) {
        User user = userRepository.findUserById(userRole.getUserId());
        user.setRoleId(userRole.getRoleId());

        return userRole;
    }

}
