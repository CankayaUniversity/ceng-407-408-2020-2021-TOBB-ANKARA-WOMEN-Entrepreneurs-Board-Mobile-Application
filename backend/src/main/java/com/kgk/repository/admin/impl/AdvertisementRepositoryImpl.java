package com.kgk.repository.admin.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.kgk.model.admin.Advertisement;
import com.kgk.repository.admin.AdvertisementRepository;

import javax.inject.Singleton;
import java.util.Collection;

@Singleton
public class AdvertisementRepositoryImpl implements AdvertisementRepository {
    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    public AdvertisementRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config) {
        this.mapper = mapper;
        this.config = config;
    }
    public Collection<Advertisement> listAllAdvertisements() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return mapper.scan(Advertisement.class, scanExpression);
    }



    public void saveAdvertisement(Advertisement advertisement) {

        mapper.save(advertisement);
    }

    public void updateAdvertisement(Advertisement advertisement) {

        //SOS

    }

    public void deleteUser(Advertisement advertisement) { mapper.delete(advertisement); }

}
