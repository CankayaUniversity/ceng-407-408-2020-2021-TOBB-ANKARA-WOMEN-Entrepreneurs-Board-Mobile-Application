package com.kgk.repository.chat.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kgk.model.chat.GroupMember;
import com.kgk.repository.chat.GroupMemberRepository;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class GroupMemberRepositoryImpl implements GroupMemberRepository {

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    public GroupMemberRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config) {
        this.mapper = mapper;
        this.config = config;
    }

    public Collection<GroupMember> listAllUsersByGroupId(String groupId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":groupId", new AttributeValue().withS(groupId));

        DynamoDBQueryExpression<GroupMember> queryExpression = new DynamoDBQueryExpression<GroupMember>()
                .withKeyConditionExpression("groupId = :groupId")
                .withExpressionAttributeValues(eav);

        return mapper.query(GroupMember.class, queryExpression);
    }

    public GroupMember addUser(String userId, String groupId) {
        GroupMember groupMember = new GroupMember();
        groupMember.setGroupId(groupId);
        groupMember.setUserId(userId);
        groupMember.setJoinedAt(System.currentTimeMillis());

        mapper.save(groupMember);
        //return mapper.load(GroupMember.class, groupMember.getUserId());
        return groupMember;
    }

    public void removeUser(String userId) {
        GroupMember groupMember = mapper.load(GroupMember.class, userId, config);
        mapper.delete(groupMember);
    }

}
