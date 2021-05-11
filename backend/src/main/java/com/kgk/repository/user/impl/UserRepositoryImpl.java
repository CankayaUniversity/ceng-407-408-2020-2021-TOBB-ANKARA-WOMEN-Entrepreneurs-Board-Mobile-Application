package com.kgk.repository.user.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kgk.model.user.Catalog;
import com.kgk.model.DeletedItem;
import com.kgk.model.user.Password;
import com.kgk.model.user.User;
import com.kgk.repository.user.CatalogRepository;
import com.kgk.repository.user.UserRepository;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.core.util.StringUtils;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class UserRepositoryImpl implements UserRepository {

    private static final String TABLE_NAME = "Users";

    private static final String GSI_NAME = "userByEmail";

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    private final CatalogRepository catalogRepository;

    public UserRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config, CatalogRepository catalogRepository) {
        this.mapper = mapper;
        this.config = config;
        this.catalogRepository = catalogRepository;
    }

    @Override
    public List<User> listAllUsers() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<User> users = mapper.scan(User.class, scanExpression).stream().collect(Collectors.toList());
        users.forEach(
                user -> user.setCatalogList(catalogRepository.listCatalogsByUserId(user.getUserId()))
        );

        return users;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":email", new AttributeValue().withS(email));

        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withKeyConditionExpression("email = :email")
                .withIndexName(GSI_NAME)
                .withExpressionAttributeValues(eav)
                .withConsistentRead(false);

        return mapper.query(User.class, queryExpression).stream().findFirst();
    }

    @Override
    public User findUserById(String userId) {
        User user = mapper.load(User.class, userId, config);
        user.setCatalogList(catalogRepository.listCatalogsByUserId(userId));

        return user;
    }

    @Override
    public User updateUser(String userId, User user) {
        User userRetrieved = mapper.load(User.class, userId, config);
        userRetrieved.copyFrom(user);

        if (CollectionUtils.isNotEmpty(user.getCatalogList())) {
            List<Catalog> oldCatalogs = catalogRepository.listCatalogsByUserId(userId);

            if (CollectionUtils.isNotEmpty(oldCatalogs)) {
                oldCatalogs.stream()
                        .forEach(
                                oldCatalog -> catalogRepository.deleteCatalog(userId, oldCatalog.getCatalogId())
                        );
            }

            userRetrieved.setCatalogList(
                    user.getCatalogList()
                            .stream()
                            .map(catalog -> catalogRepository.addCatalog(userId, catalog))
                            .collect(Collectors.toList())
            );
        }

        mapper.save(userRetrieved);
        System.out.println("[USER REPO] User is updated");

        return userRetrieved;
    }

    @Override
    public User changePassword(String userId, Password changedPassword) {
        User user = findUserById(userId);

        if (StringUtils.isNotEmpty(changedPassword.getOldPassword())) {
            if (changedPassword.getOldPassword().equals(user.getPassword())) {
                user.setPassword(changedPassword.getNewPassword());

                mapper.save(user);
                System.out.println("[USER REPO] User's password is updated");

                return user;
            }
        }
        System.out.println("[USER REPO] Either old password is null, or it does not match");

        return null;
    }

    @Override
    public void deleteUser(String userId) {
        User user = findUserById(userId);
        List<Catalog> catalogs = catalogRepository.listCatalogsByUserId(userId);

        if (CollectionUtils.isNotEmpty(catalogs)) {
            catalogs.stream()
                    .forEach(
                            catalog -> catalogRepository.deleteCatalog(userId, catalog.getCatalogId())
                    );
        }

        DeletedItem deletedUser = new DeletedItem();
        deletedUser.setDeletedTime(System.currentTimeMillis());
        deletedUser.setWhichTable(TABLE_NAME);
        deletedUser.setOriginalId(user.getUserId());

        try {
            //Creating the ObjectMapper object
            ObjectMapper om = new ObjectMapper();
            //Converting the Object to JSONString
            String json = om.writeValueAsString(user);
            deletedUser.setJson(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapper.save(deletedUser);
        System.out.println("[USER REPO] Deleted user is saved to DeletedItems table");

        mapper.delete(user);
        System.out.println("[USER REPO] User is deleted");
    }

}
