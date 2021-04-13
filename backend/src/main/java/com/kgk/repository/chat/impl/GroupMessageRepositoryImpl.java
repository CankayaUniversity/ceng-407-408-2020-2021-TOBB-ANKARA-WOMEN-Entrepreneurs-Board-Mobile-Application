package com.kgk.repository.chat.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kgk.model.DeletedItem;
import com.kgk.model.chat.GroupMessage;
import com.kgk.repository.chat.GroupMessageRepository;

import javax.inject.Singleton;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Singleton
public class GroupMessageRepositoryImpl implements GroupMessageRepository {

    private static final String TABLE_NAME = "GroupMessages";

    private static final String LSI_NAME = "groupsByCreatedBy";

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    public GroupMessageRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config) {
        this.mapper = mapper;
        this.config = config;
    }

    @Override
    public List<GroupMessage> listAllMessagesByGroupId(String groupId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":groupId", new AttributeValue().withS(groupId));
        eav.put(":sendAt", new AttributeValue().withN(String.valueOf(System.currentTimeMillis())));

        DynamoDBQueryExpression<GroupMessage> queryExpression = new DynamoDBQueryExpression<GroupMessage>()
                .withIndexName(LSI_NAME)
                .withKeyConditionExpression("groupId = :groupId and sendAt > :sendAt")
                .withExpressionAttributeValues(eav);

        return mapper.query(GroupMessage.class, queryExpression);
    }

    @Override
    public GroupMessage saveMessage(String groupId, GroupMessage groupMessage) {
        groupMessage.setMessageId(UUID.randomUUID().toString());
        groupMessage.setGroupId(groupId);
        groupMessage.setSendAt(System.currentTimeMillis());
        //groupMessage.setSentBy(CurrentUser.getUserId());
        groupMessage.setSentBy("7320be64-69f5-4f92-8fa3-40f455b457fa");

        mapper.save(groupMessage);
        System.out.println("[GROUP MESSAGE REPO] Message is saved");

        return groupMessage;
    }

    @Override
    public void deleteMessage(String groupId, String messageId) {
        GroupMessage groupMessage = mapper.load(GroupMessage.class, groupId, messageId, config);

        DeletedItem deletedMessage = new DeletedItem();
        deletedMessage.setDeletedTime(System.currentTimeMillis());
        deletedMessage.setWhichTable(TABLE_NAME);
        deletedMessage.setOriginalId(groupMessage.getMessageId());

        try {
            //Creating the ObjectMapper object
            ObjectMapper om = new ObjectMapper();
            //Converting the Object to JSONString
            String json = om.writeValueAsString(groupMessage);
            deletedMessage.setJson(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapper.save(deletedMessage);
        System.out.println("[GROUP MESSAGE REPO] Deleted message is saved to DeletedItems table");

        mapper.delete(groupMessage);
        System.out.println("[GROUP MESSAGE REPO] Message is deleted");
    }

}
