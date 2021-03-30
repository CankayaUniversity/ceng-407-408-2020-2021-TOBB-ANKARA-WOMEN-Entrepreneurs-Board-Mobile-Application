package com.kgk.repository.chat.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kgk.model.chat.GroupMessage;
import com.kgk.repository.chat.GroupMessageRepository;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class GroupMessageRepositoryImpl implements GroupMessageRepository {

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    public GroupMessageRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config) {
        this.mapper = mapper;
        this.config = config;
    }

    public Collection<GroupMessage> listAllMessagesByGroupId(String groupId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":groupId", new AttributeValue().withS(groupId));

        DynamoDBQueryExpression<GroupMessage> queryExpression = new DynamoDBQueryExpression<GroupMessage>()
                .withKeyConditionExpression("groupId = :groupId")
                .withExpressionAttributeValues(eav);

        return mapper.query(GroupMessage.class, queryExpression);
    }

    public GroupMessage saveMessage(String groupId, String message) {
        GroupMessage groupMessage = new GroupMessage();
        groupMessage.setGroupId(groupId);
        groupMessage.setMessage(message);
        groupMessage.setSendAt(System.currentTimeMillis());
        mapper.save(groupMessage);

        return groupMessage;
    }

    public void deleteMessage(String groupId, String messageId) {
        GroupMessage groupMessage = mapper.load(GroupMessage.class, messageId, config);
        mapper.delete(groupMessage);
    }

}
