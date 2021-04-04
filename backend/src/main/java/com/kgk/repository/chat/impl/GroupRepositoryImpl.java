package com.kgk.repository.chat.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
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
        group.setCreatedBy("9c9f4880-c0e6-4afa-9040-b8f5facf35ef");
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
                groupMember -> mapper.delete(groupMember)
        );

        List<GroupMessage> groupMessages = groupMessageRepository.listAllMessagesByGroupId(groupId);
        groupMessages.forEach(
                groupMessage -> mapper.delete(groupMessage)
        );

        mapper.delete(group);
    }

}
