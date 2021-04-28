package com.kgk.repository.chat;

import com.kgk.model.chat.GroupMessage;

import java.util.List;

public interface GroupMessageRepository {

    List<GroupMessage> listAllMessagesByGroupId(String groupId); //shows all messages of a group chat

    GroupMessage saveMessage(String groupId, GroupMessage groupMessage); //saves the sent message to database

    void deleteMessage(String groupId, String messageId); //removes the deleted message from database

}
