package com.kgk.web.chat;

import com.kgk.model.chat.*;
import com.kgk.repository.chat.*;
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

    public GroupController(GroupRepository groupRepository, GroupMemberRepository groupMemberRepository, GroupMessageRepository groupMessageRepository) {
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.groupMessageRepository = groupMessageRepository;
    }

    //GroupRepository methods
    @Get("/{createdBy}/{city}")
    public List<Group> listAllGroupsByUserId(@PathVariable("createdBy") String createdBy, @PathVariable("city") String city) {
        return groupRepository.listAllGroupsByUserId(createdBy, city);
    }

    @Get("/{groupId}")
    public Group showGroup(@PathVariable("groupId") String groupId) {
        return groupRepository.findGroupByGroupId(groupId);
    }

    @Post
    public Group createGroup(@Valid @Body Group group) {
        return groupRepository.createGroup(group);
    }

    @Put("/{groupId}/{city}")
    public Group updateGroup(@PathVariable("groupId") String groupId, @PathVariable("city") String city,
                             @Valid @Body Group group){
        return groupRepository.updateGroup(groupId, city, group);
    }

    @Delete("/{groupId}/{city}")
    public void deleteGroup(@PathVariable("groupId") String groupId, @PathVariable("city") String city) {
        groupRepository.deleteGroup(groupId, city);
    }

    //GroupMemberRepository methods
    @Get("/{groupId}")
    public List<GroupMember> listAllUsersByGroupId(@PathVariable("groupId") String groupId) {
        return groupMemberRepository.listAllUsersByGroupId(groupId);
    }

    @Get("/{userId}")
    public List<Group> listAllGroupsThatUserIsIn(@PathVariable("userId") String userId) {
        return groupMemberRepository.listAllGroupsByUserId(userId);
    }

    @Post("/{userId}/{groupId}")
    public GroupMember addUser(@PathVariable("userId") String userId, @PathVariable("groupId") String groupId) {
        return groupMemberRepository.addUser(userId, groupId);
    }

    @Delete("/{userId}")
    public void removeUser(@PathVariable("userId") String userId) {
        groupMemberRepository.removeUser(userId);
    }

    //GroupMessageRepository methods
    @Get("/messages/{groupId}")
    public List<GroupMessage> listAllMessagesByGroupId(@PathVariable("groupId") String groupId) {
        return groupMessageRepository.listAllMessagesByGroupId(groupId);
    }

    @Post("/messages/{groupId}")
    public GroupMessage sendMessage(@PathVariable("groupId") String groupId, String message) {
        return groupMessageRepository.saveMessage(groupId, message);
    }

    @Delete("/messages/{groupId}/{messageId}")
    public void deleteMessage(@PathVariable("groupId") String groupId, @PathVariable("messageId") String messageId) {
        groupMessageRepository.deleteMessage(groupId, messageId);
    }

}
