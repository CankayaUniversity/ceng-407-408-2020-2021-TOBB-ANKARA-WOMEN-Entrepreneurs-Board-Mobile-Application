package com.kgk.repository.chat;

import com.kgk.model.chat.Group;

import java.util.List;

public interface GroupRepository {

    List<Group> listAllGroupsByUserId(String userId); //lists all the groups that the user belongs

    Group createGroup(Group group); //saves the created group to database

    Group updateGroup(Group group); //update group info on database

    void deleteGroup(String groupId); //delete group from database
}
