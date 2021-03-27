package com.kgk.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kgk.model.Catalog;
import com.kgk.repository.CatalogRepository;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class CatalogRepositoryImpl implements CatalogRepository {

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    public CatalogRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config) {
        this.mapper = mapper;
        this.config = config;
    }


    @Override
    public List<Catalog> listCatalogsByUserId(String userId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":userId", new AttributeValue().withS(userId));

        DynamoDBQueryExpression<Catalog> queryExpression = new DynamoDBQueryExpression<Catalog>()
                .withKeyConditionExpression("userId = :userId")
                .withExpressionAttributeValues(eav);

        return mapper.query(Catalog.class, queryExpression);
    }

    @Override
    public Catalog findCatalogByCatalogId(String catalogId) {
        return mapper.load(Catalog.class, catalogId, config);
    }

    @Override
    public Catalog addCatalog(Catalog catalog) {
        mapper.save(catalog);
        return mapper.load(Catalog.class, catalog.getCatalogId());
    }

    /*@Override
    public Catalog updateCatalog(String catalogId, Catalog catalog) {
        mapper.save(catalog);
        return catalog;
        // return mapper.load(Catalog.class, catalog.getCatalogId());
    }*/

    @Override
    public void deleteCatalog(String catalogId) {
        Catalog catalog = mapper.load(Catalog.class, catalogId, config);
        mapper.delete(catalog);
    }
}
