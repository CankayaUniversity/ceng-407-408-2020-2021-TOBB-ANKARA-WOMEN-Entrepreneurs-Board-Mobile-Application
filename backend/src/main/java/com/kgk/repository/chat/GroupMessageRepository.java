package com.kgk.repository.chat;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.kgk.model.chat.GroupMessage;

import java.util.List;

public interface GroupMessageRepository {

    List<GroupMessage> listAllMessagesByGroupId(String groupId); //shows all messages of a group chat

    GroupMessage saveMessage(/*AwsProxyRequest awsRequest,*/ String groupId, GroupMessage groupMessage); //saves the sent message to database

    void deleteMessage(String groupId, String messageId); //removes the deleted message from database

}
