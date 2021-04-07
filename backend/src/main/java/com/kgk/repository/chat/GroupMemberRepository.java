package com.kgk.repository.chat;

import com.kgk.model.chat.GroupMember;

import java.util.List;

public interface GroupMemberRepository {

    List<GroupMember> listAllUsersByGroupId(String groupId); //list all users of a specific group

    GroupMember addUser(String userId, String groupId); //add users to a group

    void removeUser(String userId); //remove users from a group

}
