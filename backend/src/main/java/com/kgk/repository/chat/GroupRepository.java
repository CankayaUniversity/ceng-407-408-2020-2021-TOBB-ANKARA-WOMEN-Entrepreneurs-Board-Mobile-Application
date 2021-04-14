package com.kgk.repository.chat;

import com.kgk.model.chat.Group;

import java.util.List;

public interface GroupRepository {

    List<Group> listAllCreatedGroupsByUser(String createdBy); //lists all the groups that user created

    Group findGroupByGroupId(String groupId);

    Group createGroup(Group group);

    Group updateGroup(String groupId, Group group);

    void deleteGroup(String groupId);
}
