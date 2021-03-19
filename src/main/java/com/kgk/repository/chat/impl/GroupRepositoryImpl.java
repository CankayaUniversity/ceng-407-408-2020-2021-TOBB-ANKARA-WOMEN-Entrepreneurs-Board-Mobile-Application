package com.kgk.repository.chat.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kgk.model.chat.Group;
import com.kgk.repository.chat.GroupRepository;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class GroupRepositoryImpl implements GroupRepository {

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    public GroupRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config) {
        this.mapper = mapper;
        this.config = config;
    }

    public Collection<Group> listAllGroupsByUserId(String userId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":userId", new AttributeValue().withS(userId));

        DynamoDBQueryExpression<Group> queryExpression = new DynamoDBQueryExpression<Group>()
                .withKeyConditionExpression("userId = :userId")
                .withExpressionAttributeValues(eav);

        return mapper.query(Group.class, queryExpression);
    }

    public Group createGroup(Group group) {
        mapper.save(group);
        return mapper.load(Group.class, group.getGroupId());
    }

    public Group updateGroup(Group group) {
        Group groupRetrieved = mapper.load(Group.class, group.getGroupId());
        mapper.save(groupRetrieved);

        return groupRetrieved;
    }

    public void deleteGroup(String groupId) {
        Group group = mapper.load(Group.class, groupId, config);
        mapper.delete(group);
    }

}
