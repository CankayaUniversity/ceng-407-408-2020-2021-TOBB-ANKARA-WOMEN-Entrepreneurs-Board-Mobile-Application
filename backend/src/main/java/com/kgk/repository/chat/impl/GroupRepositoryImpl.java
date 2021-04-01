package com.kgk.repository.chat.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kgk.model.chat.Group;
import com.kgk.model.chat.GroupMember;
import com.kgk.repository.chat.GroupMemberRepository;
import com.kgk.repository.chat.GroupMessageRepository;
import com.kgk.repository.chat.GroupRepository;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Group> listAllGroupsByUserId(String userId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":userId", new AttributeValue().withS(userId));

        DynamoDBQueryExpression<Group> queryExpression = new DynamoDBQueryExpression<Group>()
                .withKeyConditionExpression("userId = :userId")
                .withExpressionAttributeValues(eav);

        return mapper.query(Group.class, queryExpression).stream().collect(Collectors.toList());
    }

    public Group createGroup(Group group) {
        group.setCreatedAt(System.currentTimeMillis());
        //group.setCreatedBy(currentUser);
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
        List<GroupMember> groupMembers = groupMemberRepository.listAllUsersByGroupId(groupId);

        mapper.delete(group);
    }

}
