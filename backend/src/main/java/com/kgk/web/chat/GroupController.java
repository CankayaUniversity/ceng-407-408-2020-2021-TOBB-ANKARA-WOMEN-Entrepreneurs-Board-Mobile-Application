package com.kgk.web.chat;

import com.kgk.model.chat.*;
import com.kgk.repository.chat.*;
import io.micronaut.http.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

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

    @Get("/{userId}")
    public Collection<Group> listAllGroupsByUserId(@PathVariable("userId") String userId) {
        return groupRepository.listAllGroupsByUserId(userId);
    }

    @Post
    public Group createGroup(@Valid @Body Group group) {
        return groupRepository.createGroup(group);
    }

    @Put
    public Group updateGroup(@Valid @Body Group group){
        return groupRepository.updateGroup(group);
    }

    @Delete("/{groupId}")
    public void deleteGroup(@PathVariable("groupId") String groupId) {
        groupRepository.deleteGroup(groupId);
    }

    @Get("/{groupId}")
    public Collection<GroupMember> listAllMembersByGroupId(@PathVariable("groupId") String groupId) {
        return groupMemberRepository.listAllMembersByGroupId(groupId);
    }

    @Post("/{userId}/{groupId}")
    public GroupMember addMember(@PathVariable("userId") String userId, @PathVariable("groupId") String groupId) {
        return groupMemberRepository.addMember(userId, groupId);
    }

    @Delete("/{userId}")
    public void removeMember(@PathVariable("userId") String userId) {
        groupMemberRepository.removeMember(userId);
    }

    @Get("/messages/{groupId}")
    public Collection<GroupMessage> listAllMessagesByGroupId(@PathVariable("groupId") String groupId) {
        return groupMessageRepository.listAllMessagesByGroupId(groupId);
    }

    @Post("/messages/{groupId}")
    public GroupMessage saveMessage(@PathVariable("groupId") String groupId, String message) {
        return groupMessageRepository.saveMessage(groupId, message);
    }

    @Delete("/messages/{groupId}/{messageId}")
    public void deleteMessage(@PathVariable("groupId") String groupId, @PathVariable("messageId") String messageId) {
        groupMessageRepository.deleteMessage(groupId, messageId);
    }

}
