package com.kgk.repository.user.impl;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.kgk.model.user.User;
import com.kgk.repository.user.CatalogRepository;
import com.kgk.repository.user.CurrentUserRepository;

import javax.inject.Singleton;

@Singleton
public class CurrentUserRepositoryImpl implements CurrentUserRepository {

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    public CurrentUserRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config) {
        this.mapper = mapper;
        this.config = config;
    }

    @Override
    public User findCurrentUser(AwsProxyRequest awsRequest) {
        String userId = awsRequest.getRequestContext().getAuthorizer().getClaims().getSubject();
        User currentUser = mapper.load(User.class, userId, config);

        return currentUser;
    }

}
