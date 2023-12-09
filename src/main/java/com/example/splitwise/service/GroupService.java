package com.example.splitwise.service;

import com.example.splitwise.dtos.AddGroupRequestDto;
import com.example.splitwise.dtos.AdminAccessRequiredException;
import com.example.splitwise.dtos.GroupMemberRequestDto;
import com.example.splitwise.exceptions.GroupNotFoundException;
import com.example.splitwise.exceptions.UserNotFoundException;
import com.example.splitwise.models.Group;
import com.example.splitwise.models.User;
import com.example.splitwise.repository.GroupRepository;
import com.example.splitwise.repository.UserRepository;
import org.antlr.v4.runtime.misc.Pair;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    GroupRepository groupRepository;
    UserRepository userRepository;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public Group createGroup(AddGroupRequestDto addGroupRequestDto) throws UserNotFoundException {
        Group group=new Group();
        User user;
        Optional<User> userOptional=userRepository.findUserById(addGroupRequestDto.getAdminId());
        if(userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        user=userOptional.get();
        group.setAdmin(user);
        group.setName(addGroupRequestDto.getName());
        group.setUsers(List.of(user));
        return groupRepository.save(group);
    }

    public Pair<Group,User> addMemberToGroup(GroupMemberRequestDto groupMemberRequestDto) throws GroupNotFoundException, UserNotFoundException, AdminAccessRequiredException {
        User user;
        Optional<User> userOptional=userRepository.findUserById(groupMemberRequestDto.getMemberId());
        if(userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        user=userOptional.get();

        Group group;
        Optional<Group> groupOptional=groupRepository.findGroupById(groupMemberRequestDto.getGroupId());
        if(groupOptional.isEmpty()){
            throw new GroupNotFoundException();
        }
        group=groupOptional.get();

        if(group.getAdmin().getId()==groupMemberRequestDto.getAdminId()){
            group.getUsers().add(user);
            groupRepository.save(group);
        }
        else{
            throw new AdminAccessRequiredException();
        }
        return new Pair<>(group,user);
    }
}
