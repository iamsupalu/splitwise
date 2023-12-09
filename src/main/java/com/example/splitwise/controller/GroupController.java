package com.example.splitwise.controller;

import com.example.splitwise.dtos.*;
import com.example.splitwise.exceptions.GroupNotFoundException;
import com.example.splitwise.exceptions.UserNotFoundException;
import com.example.splitwise.models.Group;
import com.example.splitwise.models.User;
import com.example.splitwise.service.GroupService;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Controller;

@Controller
public class GroupController {
    GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    public AddGroupResponseDto createGroup(AddGroupRequestDto addGroupRequestDto){
        AddGroupResponseDto addGroupResponseDto=new AddGroupResponseDto();

        try{
            Group group=groupService.createGroup(addGroupRequestDto);
            addGroupResponseDto.setId(group.getId());
            addGroupResponseDto.setName(group.getName());
            addGroupResponseDto.setAdmin(group.getAdmin().getName());
        }
        catch(UserNotFoundException userNotFoundException){
            addGroupResponseDto.setErrorMessage("Invalid User!");
        }
        return addGroupResponseDto;
    }

    public GroupMemberResponseDto addMember(GroupMemberRequestDto groupMemberRequestDto){

        GroupMemberResponseDto groupMemberResponseDto=new GroupMemberResponseDto();
        try{
            Pair<Group, User> userGrouppair=groupService.addMemberToGroup(groupMemberRequestDto);
            groupMemberResponseDto.setMemberName(userGrouppair.b.getName());
            groupMemberResponseDto.setAdminName(userGrouppair.a.getAdmin().getName());
            groupMemberResponseDto.setGroupName(userGrouppair.a.getName());
        }
        catch(UserNotFoundException userNotFoundException){
            groupMemberResponseDto.setErrorMessage("Invalid User!");
        }
        catch(GroupNotFoundException groupNotFoundException){
            groupMemberResponseDto.setErrorMessage("Invalid Group!");
        }
        catch(AdminAccessRequiredException adminAccessRequiredException){
            groupMemberResponseDto.setErrorMessage("Group admin can add members");
        }
        return groupMemberResponseDto;
    }
}
