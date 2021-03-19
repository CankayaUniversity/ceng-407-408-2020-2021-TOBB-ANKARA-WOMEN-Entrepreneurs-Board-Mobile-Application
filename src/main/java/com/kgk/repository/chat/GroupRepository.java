package com.kgk.repository.chat;

import com.kgk.model.chat.Group;

import java.util.Collection;

public interface GroupRepository {

    Collection<Group> listAllGroupsByUserId(String userId);

    Group createGroup(Group group);

    Group updateGroup(Group group);

    void deleteGroup(String groupId);
}
