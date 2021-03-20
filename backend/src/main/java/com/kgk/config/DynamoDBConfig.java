package com.kgk.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

@Factory
public class DynamoDBConfig {

    @Singleton
    public AmazonDynamoDB dynamoDBClient() {
      return AmazonDynamoDBClientBuilder.standard().withRegion(Regions.EU_WEST_1).build();
    }

    @Singleton
    public DynamoDBMapper dynamoDBmapper(AmazonDynamoDB dynamoDBClient) {
      return new DynamoDBMapper(dynamoDBClient);
    }

    @Singleton
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
      return DynamoDBMapperConfig.builder()
          .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
          .build();
    }

    @Singleton
    public DynamoDB dynamoDB(AmazonDynamoDB dynamoDBClient) { return new DynamoDB(dynamoDBClient); }

}
