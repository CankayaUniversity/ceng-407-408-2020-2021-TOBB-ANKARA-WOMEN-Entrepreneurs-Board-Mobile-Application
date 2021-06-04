package com.kgk.repository.admin.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.kgk.repository.admin.AdvertisementRepository;

import javax.inject.Singleton;

@Singleton
public class AdvertisementRepositoryImpl implements AdvertisementRepository {

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    public AdvertisementRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config) {
        this.mapper = mapper;
        this.config = config;
    }

}
