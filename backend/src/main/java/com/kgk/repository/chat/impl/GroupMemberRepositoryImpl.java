package com.kgk.repository.chat.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kgk.model.DeletedItem;
import com.kgk.model.chat.Group;
import com.kgk.model.chat.GroupMember;
import com.kgk.repository.chat.GroupMemberRepository;
import com.kgk.repository.chat.GroupRepository;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class GroupMemberRepositoryImpl implements GroupMemberRepository {

    private static final String TABLE_NAME = "GroupMembers";

    private static final String GSI_NAME = "groupMembersByUserId";

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    private final GroupRepository groupRepository;

    public GroupMemberRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config, GroupRepository groupRepository) {
        this.mapper = mapper;
        this.config = config;
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> listAllGroupsByUserId(String userId) {
        List<Group> groups = new ArrayList<>();
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":userId", new AttributeValue().withS(userId));

        DynamoDBQueryExpression<GroupMember> queryExpression = new DynamoDBQueryExpression<GroupMember>()
                .withIndexName(GSI_NAME)
                .withKeyConditionExpression("userId = :userId")
                .withExpressionAttributeValues(eav)
                .withConsistentRead(false);

        List<GroupMember> groupMembers = mapper.query(GroupMember.class, queryExpression);
        groupMembers.forEach(
                groupMember ->
                    groups.add(groupRepository.findGroupByGroupId(groupMember.getGroupId()))
        );

        return groups;
    }

    @Override
    public List<GroupMember> listAllUsersByGroupId(String groupId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":groupId", new AttributeValue().withS(groupId));

        DynamoDBQueryExpression<GroupMember> queryExpression = new DynamoDBQueryExpression<GroupMember>()
                .withKeyConditionExpression("groupId = :groupId")
                .withExpressionAttributeValues(eav);

        return mapper.query(GroupMember.class, queryExpression);
    }

    @Override
    public GroupMember addUser(String userId, String groupId) {
        GroupMember groupMember = new GroupMember();
        groupMember.setGroupId(groupId);
        groupMember.setUserId(userId);
        groupMember.setJoinedAt(System.currentTimeMillis());

        mapper.save(groupMember);
        System.out.println("[GROUP MEMBER REPO] Member is added");

        return groupMember;
    }

    @Override
    public void removeUser(String userId) {
        GroupMember groupMember = mapper.load(GroupMember.class, userId, config);

        DeletedItem deletedMember = new DeletedItem();
        deletedMember.setDeletedTime(System.currentTimeMillis());
        deletedMember.setWhichTable(TABLE_NAME);
        deletedMember.setOriginalId(groupMember.getUserId());

        try {
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(groupMember);
            deletedMember.setJson(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapper.save(deletedMember);
        System.out.println("[GROUP MEMBER REPO] Removed member is saved to DeletedItems table");

        mapper.delete(groupMember);
        System.out.println("[GROUP MEMBER REPO] Member is removed");
    }

}
