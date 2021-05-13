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
import com.kgk.model.user.User;
import com.kgk.repository.chat.GroupMemberRepository;
import com.kgk.repository.chat.GroupMessageRepository;
import com.kgk.repository.chat.GroupRepository;
import com.kgk.repository.user.UserRepository;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Singleton
public class GroupRepositoryImpl implements GroupRepository {

    private static final String TABLE_NAME = "Groups";

    private static final String GSI_NAME = "groupsByCreatedBy";

    private final DynamoDBMapper mapper;

    private final DynamoDBMapperConfig config;

    private final UserRepository userRepository;

    private final GroupMessageRepository groupMessageRepository;

    private final GroupMemberRepository groupMemberRepository;

    public GroupRepositoryImpl(DynamoDBMapper mapper, DynamoDBMapperConfig config, UserRepository userRepository) {
        this.mapper = mapper;
        this.config = config;
        this.userRepository = userRepository;
        this.groupMessageRepository = new GroupMessageRepositoryImpl(this.mapper, this.config);
        this.groupMemberRepository = new GroupMemberRepositoryImpl(this.mapper, this.config, this);
    }

    @Override
    public Group findGroupByGroupId(String groupId) {
        return mapper.load(Group.class, groupId, config);
    }

    @Override
    public List<Group> listAllCreatedGroupsByUser(String userId) {
        User currentUser = userRepository.findUserById(userId);

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":createdBy", new AttributeValue().withS(currentUser.getUserId()));
        eav.put(":city", new AttributeValue().withS(currentUser.getCity()));

        DynamoDBQueryExpression<Group> queryExpression = new DynamoDBQueryExpression<Group>()
                .withIndexName(GSI_NAME)
                .withKeyConditionExpression("createdBy = :createdBy and city = :city")
                .withExpressionAttributeValues(eav)
                .withConsistentRead(false);

        return mapper.query(Group.class, queryExpression);
    }

    @Override
    public Group createGroup(String userId, Group group) {
        User currentUser = userRepository.findUserById(userId);

        group.setGroupId(UUID.randomUUID().toString());
        group.setCreatedAt(System.currentTimeMillis());
        group.setCreatedBy(currentUser.getUserId());
        group.setCity(currentUser.getCity());
        mapper.save(group);

        return group;
    }

    @Override
    public Group updateGroup(String groupId, Group group) {
        Group groupRetrieved = mapper.load(Group.class, groupId, config);
        groupRetrieved.setGroupName(group.getGroupName());
        groupRetrieved.setGroupDesc(group.getGroupDesc());
        mapper.save(groupRetrieved);

        return groupRetrieved;
    }

    @Override
    public void deleteGroup(String groupId) {
        Group group = mapper.load(Group.class, groupId, config);

        List<GroupMember> groupMembers = groupMemberRepository.listAllUsersByGroupId(groupId);
        groupMembers.forEach(
                groupMember -> groupMemberRepository.removeUser(groupMember.getUserId())
        );

        List<GroupMessage> groupMessages = groupMessageRepository.listAllMessagesByGroupId(groupId);
        groupMessages.forEach(
                groupMessage -> groupMessageRepository.deleteMessage(groupId, groupMessage.getMessageId())
        );

        DeletedItem deletedGroup = new DeletedItem();
        deletedGroup.setDeletedTime(System.currentTimeMillis());
        deletedGroup.setWhichTable(TABLE_NAME);
        deletedGroup.setOriginalId(group.getGroupId());

        try {
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(group);
            deletedGroup.setJson(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapper.save(deletedGroup);
        System.out.println("[GROUP REPO] Deleted group is saved to DeletedItems table");

        mapper.delete(group);
        System.out.println("[GROUP REPO] Group is deleted");
    }

}
