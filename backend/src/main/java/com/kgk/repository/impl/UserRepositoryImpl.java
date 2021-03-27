package com.kgk.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kgk.model.User;
import com.kgk.repository.UserRepository;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.core.util.StringUtils;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class UserRepositoryImpl implements UserRepository {
    
    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    public UserRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config) {
        this.mapper = mapper;
        this.config = config;
    }

    public Collection<User> listAllUsers() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        Collection<User> users = mapper.scan(User.class, scanExpression);
        return users;
    }

    public Collection<User> findUsersByRoleId(String roleId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":roleId", new AttributeValue().withS(roleId));

        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withKeyConditionExpression("roleId = :roleId")
                .withExpressionAttributeValues(eav);

        return mapper.query(User.class, queryExpression);
    }

    @Override
    public User findUserById(String userId) {
        return mapper.load(User.class, userId, config);
    }

    public User saveUser(User user) {
        user.setRoleId("101"); //roleId'ler Roles table'ından çekilsin
        mapper.save(user);

        if (CollectionUtils.isNotEmpty(user.getCatalogList())) {

        }
        //return mapper.load(User.class, user.getUserId());
        return user;
    }

    public User updateUser(String userId, User user) {
        User userRetrieved = findUserById(userId);
        mapper.save(userRetrieved);

        return userRetrieved;
    }

    public User changePassword(String userId, String oldPassword, String newPassword) {
        User user = findUserById(userId);
        if (StringUtils.isNotEmpty(oldPassword)) {
            if (oldPassword.equals(user.getPassword())) {
                user.setPassword(newPassword);
                mapper.save(user);

                return user;
            }
        }
        return null;
    }

    public void deleteUser(String userId) {
        User user = mapper.load(User.class, userId, config);
        mapper.delete(user);
    }

}
