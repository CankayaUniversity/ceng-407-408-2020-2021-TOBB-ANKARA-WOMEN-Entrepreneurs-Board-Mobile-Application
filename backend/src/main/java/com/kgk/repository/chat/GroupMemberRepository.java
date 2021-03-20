package com.kgk.repository.chat;

import com.kgk.model.chat.GroupMember;

import java.util.Collection;

public interface GroupMemberRepository {

    Collection<GroupMember> listAllMembersByGroupId(String groupId);

    GroupMember addMember(String userId, String groupId);

    void removeMember(String userId);

}
