package com.kgk.repository.chat;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.kgk.model.chat.Group;

import java.util.List;

public interface GroupRepository {

    List<Group> listAllCreatedGroupsByUser(/*AwsProxyRequest awsRequest*/); //lists all the groups that user created

    Group findGroupByGroupId(String groupId);

    Group createGroup(/*AwsProxyRequest awsRequest,*/ Group group);

    Group updateGroup(String groupId, Group group);

    void deleteGroup(String groupId);

}
