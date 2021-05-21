package com.kgk.repository.chat;

import com.kgk.model.chat.Group;

import java.util.List;

public interface GroupRepository {

    List<Group> listAllCreatedGroupsByUser(String userId); //lists all the groups that user created

    Group findGroupByGroupId(String groupId);

    Group createGroup(String userId, Group group);

    Group updateGroup(String groupId, Group group);

    void deleteGroup(String groupId);

}
