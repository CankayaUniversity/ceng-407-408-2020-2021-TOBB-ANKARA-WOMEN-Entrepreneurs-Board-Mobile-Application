package com.kgk.web.chat;

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
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Secured(SecurityRule.IS_AUTHENTICATED)
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

    @Get
    @Secured({"GROUP_ADMIN", "FULL_ADMIN"})
    public List<Group> listAllCreatedGroupsByUser(Principal principal) {
        return groupRepository.listAllCreatedGroupsByUser(principal.getName());
    }

    @Get("/{groupId}")
    public Group findGroup(@PathVariable("groupId") String groupId) {
        return groupRepository.findGroupByGroupId(groupId);
    }

    @Post
    @Secured({"GROUP_ADMIN", "FULL_ADMIN"})
    public Group createGroup(Principal principal, @Valid @Body Group group) {
        return groupRepository.createGroup(principal.getName(), group);
    }

    @Put("/{groupId}")
    @Secured({"GROUP_ADMIN", "FULL_ADMIN"})
    public Group updateGroup(@PathVariable("groupId") String groupId, @Valid @Body Group group){
        return groupRepository.updateGroup(groupId, group);
    }

    @Delete("/{groupId}")
    @Secured({"GROUP_ADMIN", "FULL_ADMIN"})
    public void deleteGroup(@PathVariable("groupId") String groupId) {
        groupRepository.deleteGroup(groupId);
    }

    //GroupMemberRepository methods
    @Get("/group-members/{groupId}")
    @Secured({"GROUP_ADMIN", "FULL_ADMIN"})
    public List<GroupMember> listAllUsersByGroupId(@PathVariable("groupId") String groupId) {
        return groupMemberRepository.listAllUsersByGroupId(groupId);
    }

    @Get("/group-members/add-user")
    @Secured({"GROUP_ADMIN", "FULL_ADMIN"})
    public List<User> listAllUsers() {
        return userRepository.listAllUsers();
    }

    @Get("/all-groups/{userId}")
    public List<Group> listAllGroupsThatUserIsIn(@PathVariable("userId") String userId) {
        return groupMemberRepository.listAllGroupsByUserId(userId);
    }

    @Post("/{userId}/{groupId}")
    @Secured({"GROUP_ADMIN", "FULL_ADMIN"})
    public GroupMember addUser(@PathVariable("userId") String userId, @PathVariable("groupId") String groupId) {
        return groupMemberRepository.addUser(userId, groupId);
    }

    @Delete("/remove-user/{userId}")
    @Secured({"GROUP_ADMIN", "FULL_ADMIN"})
    public void removeUser(@PathVariable("userId") String userId) {
        groupMemberRepository.removeUser(userId);
    }

    //GroupMessageRepository methods
    @Get("/messages/{groupId}")
    public List<GroupMessage> listAllMessagesByGroupId(@PathVariable("groupId") String groupId) {
        return groupMessageRepository.listAllMessagesByGroupId(groupId);
    }

    @Post("/messages/{groupId}")
    public GroupMessage sendMessage(Principal principal, @PathVariable("groupId") String groupId, @Valid @Body GroupMessage groupMessage) {
        return groupMessageRepository.saveMessage(principal.getName(), groupId, groupMessage);
    }

    @Delete("/messages/{groupId}/{messageId}")
    public void deleteMessage(@PathVariable("groupId") String groupId, @PathVariable("messageId") String messageId) {
        groupMessageRepository.deleteMessage(groupId, messageId);
    }
}
