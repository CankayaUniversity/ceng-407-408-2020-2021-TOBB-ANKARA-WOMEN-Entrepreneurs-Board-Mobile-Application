package com.kgk.repository.user.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kgk.model.user.Catalog;
import com.kgk.model.DeletedItem;
import com.kgk.repository.user.CatalogRepository;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Singleton
public class CatalogRepositoryImpl implements CatalogRepository {

    private static final String TABLE_NAME = "Catalogs";

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
    public Catalog findCatalogByCatalogId(String userId, String catalogId) {
        return mapper.load(Catalog.class, userId, catalogId, config);
    }

    @Override
    public Catalog addCatalog(String userId, Catalog catalog) {
        catalog.setCatalogId(UUID.randomUUID().toString());
        catalog.setUserId(userId);
        mapper.save(catalog);
        System.out.println("[CATALOG REPO] Catalog is saved");

        return mapper.load(Catalog.class, userId, catalog.getCatalogId(), config);
    }

    @Override
    public void deleteCatalog(String userId, String catalogId) {
        Catalog catalog = mapper.load(Catalog.class, userId, catalogId, config);

        DeletedItem deletedCatalog = new DeletedItem();
        deletedCatalog.setDeletedTime(System.currentTimeMillis());
        deletedCatalog.setWhichTable(TABLE_NAME);
        deletedCatalog.setOriginalId(catalog.getCatalogId());

        try {
            //Creating the ObjectMapper object
            ObjectMapper om = new ObjectMapper();
            //Converting the Object to JSONString
            String json = om.writeValueAsString(catalog);
            deletedCatalog.setJson(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapper.save(deletedCatalog);
        System.out.println("[CATALOG REPO] Deleted catalog is saved to DeletedItems table");

        mapper.delete(catalog);
        System.out.println("[CATALOG REPO] Catalog is deleted");
    }

}
