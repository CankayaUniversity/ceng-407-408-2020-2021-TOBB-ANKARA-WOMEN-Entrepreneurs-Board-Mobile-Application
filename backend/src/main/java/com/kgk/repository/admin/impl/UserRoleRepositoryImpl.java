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
import io.micronaut.core.util.CollectionUtils;

import javax.inject.Singleton;
import java.util.ArrayList;
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
        //TODO: CurrenUser's city info must pulled
        Map<String, AttributeValue> eav = new HashMap<>();
        //eav.put(":city", new AttributeValue().withS(currentUser.getCity()));

        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withKeyConditionExpression("city = :city")
                .withExpressionAttributeValues(eav);

        Collection<User> users = mapper.query(User.class, queryExpression);

        if (CollectionUtils.isNotEmpty(users)) {
            Collection<UserRole> userRoles = new ArrayList<>();
            users.forEach(
                    user -> {
                        UserRole userRole = new UserRole();
                        userRole.setUserId(user.getUserId());
                        userRole.setRoleId(user.getRoleId());
                        userRoles.add(userRole);
                    }
            );
            return userRoles;
        }
        return null;
    }

    @Override
    public Collection<UserRole> findAllUsersWithRoleId(String roleId) {
        //TODO: CurrenUser's city info must pulled
        Map<String, AttributeValue> eav = new HashMap<>();
        //eav.put(":city", new AttributeValue().withS(currentUser.getCity()));
        eav.put(":roleId", new AttributeValue().withS(roleId));

        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withKeyConditionExpression("roleId = :roleId") //TODO: add city to key expression
                .withExpressionAttributeValues(eav);

        Collection<User> users = mapper.query(User.class, queryExpression);

        if (CollectionUtils.isNotEmpty(users)) {
            Collection<UserRole> userRoles = new ArrayList<>();
            users.forEach(
                    user -> {
                        UserRole userRole = new UserRole();
                        userRole.setUserId(user.getUserId());
                        userRole.setRoleId(user.getRoleId());
                        userRoles.add(userRole);
                    }
            );
            return userRoles;
        }
        return null;

    }

    @Override
    public UserRole changeRole(UserRole userRole) {
        User user = userRepository.findUserById(userRole.getUserId());
        user.setRoleId(userRole.getRoleId());

        return userRole;
    }

}
