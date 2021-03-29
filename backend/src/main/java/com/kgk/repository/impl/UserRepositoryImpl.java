package com.kgk.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kgk.model.Catalog;
import com.kgk.model.User;
import com.kgk.repository.CatalogRepository;
import com.kgk.repository.UserRepository;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.core.util.StringUtils;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class UserRepositoryImpl implements UserRepository {
    
    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    private final CatalogRepository catalogRepository;

    public UserRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config, CatalogRepository catalogRepository) {
        this.mapper = mapper;
        this.config = config;
        this.catalogRepository = catalogRepository;
    }

    public Collection<User> listAllUsers() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        Collection<User> users = mapper.scan(User.class, scanExpression);
        users.forEach(
                user -> user.setCatalogList(catalogRepository.listCatalogsByUserId(user.getUserId()))
        );

        return users;
    }

    public Collection<User> findUsersByRoleId(String roleId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":roleId", new AttributeValue().withS(roleId));

        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withKeyConditionExpression("roleId = :roleId")
                .withExpressionAttributeValues(eav);

        Collection<User> users = mapper.query(User.class, queryExpression);
        users.forEach(
                user -> user.setCatalogList(catalogRepository.listCatalogsByUserId(user.getUserId()))
        );

        return users;
    }

    @Override
    public User findUserById(String userId) {
        User user = mapper.load(User.class, userId, config);
        user.setCatalogList(catalogRepository.listCatalogsByUserId(userId));

        return user;
    }

    /*@Override
    public User saveUser(User user) {
        user.setRoleId("101"); //roleId'ler Roles table'ından çekilsin
        mapper.save(user);
        System.out.println("[USER REPO] User is saved");
        return user;
        //return mapper.load(User.class, user.getUserId());
    }*/

    public User updateUser(String userId, User user) {
        User userRetrieved = mapper.load(User.class, userId, user.getCity(), config);
        user.copyFrom(userRetrieved);

        if (CollectionUtils.isNotEmpty(user.getCatalogList())) {
            List<Catalog> oldCatalogs = catalogRepository.listCatalogsByUserId(userId);

            if (CollectionUtils.isNotEmpty(oldCatalogs)) {
                oldCatalogs.stream()
                        .forEach(
                                oldCatalog -> mapper.delete(oldCatalog)
                        );
            }

            user.setCatalogList(
                    user.getCatalogList()
                            .stream()
                            .map(catalog -> catalogRepository.addCatalog(userId, catalog))
                            .collect(Collectors.toList())
            );
        }

        mapper.save(user);
        System.out.println("[USER REPO] User is updated");

        return user;
    }

    public User changePassword(String userId, String oldPassword, String newPassword) {
        User user = findUserById(userId);
        if (StringUtils.isNotEmpty(oldPassword)) {
            if (oldPassword.equals(user.getPassword())) {
                user.setPassword(newPassword);
                mapper.save(user);
                System.out.println("[USER REPO] User's password is updated");

                return user;
            }
        }
        System.out.println("[USER REPO] Either old password is null, or it does not match");
        return null;
    }

    public void deleteUser(String userId) {
        User user = mapper.load(User.class, userId, config);
        List<Catalog> catalogs = catalogRepository.listCatalogsByUserId(userId);

        if (CollectionUtils.isNotEmpty(catalogs)) {
            catalogs.stream()
                    .forEach(
                            catalog -> mapper.delete(catalog)
                    );
        }

        System.out.println("[USER REPO] User is deleted");
        mapper.delete(user);
    }

}
