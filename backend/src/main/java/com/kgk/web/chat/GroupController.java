package com.kgk.web.chat;

import com.kgk.model.chat.Group;
import com.kgk.repository.chat.GroupRepository;
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
    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Get("/{createdBy}")
    public List<Group> listAllCreatedGroupsByUser(@PathVariable("createdBy") String createdBy) {
        return groupRepository.listAllCreatedGroupsByUser(createdBy);
    }

    @Get("/{groupId}")
    public Group showGroup(@PathVariable("groupId") String groupId) {
        return groupRepository.findGroupByGroupId(groupId);
    }

    @Post
    public Group createGroup(@Valid @Body Group group) {
        return groupRepository.createGroup(group);
    }

    @Put("/{groupId}")
    public Group updateGroup(@PathVariable("groupId") String groupId, @Valid @Body Group group){
        return groupRepository.updateGroup(groupId, group);
    }

    @Delete("/{groupId}")
    public void deleteGroup(@PathVariable("groupId") String groupId) {
        groupRepository.deleteGroup(groupId);
    }

}
