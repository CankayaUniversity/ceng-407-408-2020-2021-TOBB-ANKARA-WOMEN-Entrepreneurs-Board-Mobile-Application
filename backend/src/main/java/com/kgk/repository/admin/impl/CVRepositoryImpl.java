package com.kgk.repository.admin.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.kgk.repository.admin.CVRepository;

import javax.inject.Singleton;

@Singleton
public class CVRepositoryImpl implements CVRepository {

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    public CVRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config) {
        this.mapper = mapper;
        this.config = config;
    }
}
