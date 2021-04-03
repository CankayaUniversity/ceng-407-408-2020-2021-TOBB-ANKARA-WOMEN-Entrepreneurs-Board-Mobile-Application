package com.kgk.repository.chat;

import com.kgk.model.chat.Group;

import java.util.List;

public interface GroupRepository {

    List<Group> listAllGroupsByUserId(String createdBy, String city); //lists all the groups that the user belongs

    Group createGroup(Group group); //saves the created group to database

    Group updateGroup(String groupId, String city, Group group); //update group info on database

    void deleteGroup(String groupId, String city); //delete group from database
}
