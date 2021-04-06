package com.kgk.repository.admin.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kgk.model.user.User;
import com.kgk.model.admin.UserRole;
import com.kgk.repository.user.UserRepository;
import com.kgk.repository.admin.UserRoleRepository;
import io.micronaut.core.util.CollectionUtils;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<UserRole> listAllUserRoles() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        return mapper.scan(UserRole.class, scanExpression).stream().collect(Collectors.toList());
    }

    @Override
    public List<UserRole> findAllUsersWithRoleId(String roleId, String city) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":city", new AttributeValue().withS(city));
        eav.put(":roleId", new AttributeValue().withS(roleId));

        DynamoDBQueryExpression<UserRole> queryExpression = new DynamoDBQueryExpression<UserRole>()
                .withKeyConditionExpression("city = :city and roleId = :roleId")
                .withExpressionAttributeValues(eav);

        return mapper.query(UserRole.class, queryExpression);
    }

    @Override
    public UserRole changeRole(UserRole userRole) {
        //User user = userRepository.findUserById(userRole.getUserId(), );
        //user.setRoleId(userRole.getRoleId());
        //mapper.save(user);

        mapper.save(userRole);
        return userRole;
    }

}
