package com.kgk.repository.chat;

import com.kgk.model.chat.GroupMessage;

import java.util.Collection;

public interface GroupMessageRepository {

    Collection<GroupMessage> listAllMessagesByGroupId(String groupId);

    GroupMessage saveMessage(String groupId, String message);

    void deleteMessage(String groupId, String messageId);

}
