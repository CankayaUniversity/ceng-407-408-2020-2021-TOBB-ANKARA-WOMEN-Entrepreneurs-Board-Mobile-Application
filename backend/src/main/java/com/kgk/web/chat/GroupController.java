package com.kgk.web.chat;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.kgk.model.chat.Group;
import com.kgk.model.chat.GroupMember;
import com.kgk.model.chat.GroupMessage;
import com.kgk.model.user.User;
import com.kgk.repository.chat.GroupMemberRepository;
import com.kgk.repository.chat.GroupMessageRepository;
import com.kgk.repository.chat.GroupRepository;
import com.kgk.repository.user.UserRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;

@Controller("/api/group")
public class GroupController {

    private final GroupRepository groupRepository;

    private final GroupMemberRepository groupMemberRepository;

    private final GroupMessageRepository groupMessageRepository;

    private final UserRepository userRepository;

    public GroupController(GroupRepository groupRepository, GroupMemberRepository groupMemberRepository,
                           GroupMessageRepository groupMessageRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.groupMessageRepository = groupMessageRepository;
        this.userRepository = userRepository;
    }

    @Get("/created-groups")
    public List<Group> listAllCreatedGroupsByUser(/*AwsProxyRequest awsRequest*/) {
        return groupRepository.listAllCreatedGroupsByUser(/*awsRequest*/);
    }

    @Get("/{groupId}")
    public Group findGroup(@PathVariable("groupId") String groupId) {
        return groupRepository.findGroupByGroupId(groupId);
    }

    @Post
    public Group createGroup(/*AwsProxyRequest awsRequest,*/ @Valid @Body Group group) {
        return groupRepository.createGroup(/*awsRequest,*/ group);
    }

    @Put("/{groupId}")
    public Group updateGroup(@PathVariable("groupId") String groupId, @Valid @Body Group group){
        return groupRepository.updateGroup(groupId, group);
    }

    @Delete("/{groupId}")
    public void deleteGroup(@PathVariable("groupId") String groupId) {
        groupRepository.deleteGroup(groupId);
    }

    //GroupMemberRepository methods
    @Get("/group-members/{groupId}")
    public List<GroupMember> listAllUsersByGroupId(@PathVariable("groupId") String groupId) {
        return groupMemberRepository.listAllUsersByGroupId(groupId);
    }

    @Get("/group-members/add-user")
    public List<User> listAllUsers() {
        return userRepository.listAllUsers();
    }

    @Get("/all-groups/{userId}")
    public List<Group> listAllGroupsThatUserIsIn(@PathVariable("userId") String userId) {
        return groupMemberRepository.listAllGroupsByUserId(userId);
    }

    @Post("/{userId}/{groupId}")
    public GroupMember addUser(@PathVariable("userId") String userId, @PathVariable("groupId") String groupId) {
        return groupMemberRepository.addUser(userId, groupId);
    }

    @Delete("/remove-user/{userId}")
    public void removeUser(@PathVariable("userId") String userId) {
        groupMemberRepository.removeUser(userId);
    }

    //GroupMessageRepository methods
    @Get("/messages/{groupId}")
    public List<GroupMessage> listAllMessagesByGroupId(@PathVariable("groupId") String groupId) {
        return groupMessageRepository.listAllMessagesByGroupId(groupId);
    }

    @Post("/messages/{groupId}")
    public GroupMessage sendMessage(/*AwsProxyRequest awsRequest,*/ @PathVariable("groupId") String groupId, @Valid @Body GroupMessage groupMessage) {
        return groupMessageRepository.saveMessage(/*awsRequest,*/ groupId, groupMessage);
    }

    @Delete("/messages/{groupId}/{messageId}")
    public void deleteMessage(@PathVariable("groupId") String groupId, @PathVariable("messageId") String messageId) {
        groupMessageRepository.deleteMessage(groupId, messageId);
    }
}
