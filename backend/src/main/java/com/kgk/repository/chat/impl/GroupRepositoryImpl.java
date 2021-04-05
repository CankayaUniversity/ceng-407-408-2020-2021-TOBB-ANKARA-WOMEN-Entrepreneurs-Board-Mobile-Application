package com.kgk.repository.chat.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kgk.model.DeletedItem;
import com.kgk.model.chat.Group;
import com.kgk.model.chat.GroupMember;
import com.kgk.model.chat.GroupMessage;
import com.kgk.repository.chat.GroupMemberRepository;
import com.kgk.repository.chat.GroupMessageRepository;
import com.kgk.repository.chat.GroupRepository;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Singleton
public class GroupRepositoryImpl implements GroupRepository {

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    private final GroupMessageRepository groupMessageRepository;

    private final GroupMemberRepository groupMemberRepository;

    public GroupRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config, GroupMessageRepository groupMessageRepository, GroupMemberRepository groupMemberRepository) {
        this.mapper = mapper;
        this.config = config;
        this.groupMessageRepository = groupMessageRepository;
        this.groupMemberRepository = groupMemberRepository;
    }

    public List<Group> listAllGroupsByUserId(String createdBy, String city) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":createdBy", new AttributeValue().withS(createdBy));
        eav.put(":city", new AttributeValue().withS(city));

        DynamoDBQueryExpression<Group> queryExpression = new DynamoDBQueryExpression<Group>()
                .withIndexName("groupsByCreatedBy")
                .withKeyConditionExpression("createdBy = :createdBy and city = :city")
                .withExpressionAttributeValues(eav)
                .withConsistentRead(false);

        return mapper.query(Group.class, queryExpression);
    }

    public Group createGroup(Group group) {
        group.setGroupId(UUID.randomUUID().toString());
        group.setCreatedAt(System.currentTimeMillis());
        //group.setCreatedBy(currentUser.getUserId());
        //group.setCity(currentUser.getCity());
        group.setCreatedBy("d6082f95-1901-4138-8398-6ed7b2939cbe");
        group.setCity("Ankara");
        mapper.save(group);

        return mapper.load(Group.class, group.getGroupId(), group.getCity(), config);
    }

    public Group updateGroup(String groupId, String city, Group group) {
        Group groupRetrieved = mapper.load(Group.class, groupId, city, config);
        groupRetrieved.setGroupName(group.getGroupName());
        groupRetrieved.setGroupDesc(group.getGroupDesc());
        mapper.save(groupRetrieved);

        return groupRetrieved;
    }

    public void deleteGroup(String groupId, String city) {
        Group group = mapper.load(Group.class, groupId, city, config);

        List<GroupMember> groupMembers = groupMemberRepository.listAllUsersByGroupId(groupId);
        groupMembers.forEach(
                groupMember -> {
                    DeletedItem deletedMember = new DeletedItem();
                    deletedMember.setDeletedTime(System.currentTimeMillis());
                    deletedMember.setWhichTable("GroupMembers");
                    deletedMember.setOriginalId(groupMember.getUserId());

                    try {
                        //Creating the ObjectMapper object
                        ObjectMapper om = new ObjectMapper();
                        //Converting the Object to JSONString
                        String json = om.writeValueAsString(groupMember);
                        deletedMember.setJson(json);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mapper.save(deletedMember);

                    mapper.delete(groupMember);
                }
        );

        List<GroupMessage> groupMessages = groupMessageRepository.listAllMessagesByGroupId(groupId);
        groupMessages.forEach(
                groupMessage -> {
                    DeletedItem deletedMessage = new DeletedItem();
                    deletedMessage.setDeletedTime(System.currentTimeMillis());
                    deletedMessage.setWhichTable("GroupMessages");
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

                    mapper.delete(groupMessage);
                }
        );

        DeletedItem deletedGroup = new DeletedItem();
        deletedGroup.setDeletedTime(System.currentTimeMillis());
        deletedGroup.setWhichTable("Groups");
        deletedGroup.setOriginalId(group.getGroupId());

        try {
            //Creating the ObjectMapper object
            ObjectMapper om = new ObjectMapper();
            //Converting the Object to JSONString
            String json = om.writeValueAsString(group);
            deletedGroup.setJson(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapper.save(deletedGroup);

        mapper.delete(group);
    }

}
