package com.example.splitwise.Commands;

import com.example.splitwise.controller.GroupController;
import com.example.splitwise.dtos.GroupMemberRequestDto;
import com.example.splitwise.dtos.GroupMemberResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AddMemberCommand implements Command{
    GroupController groupController;

    public AddMemberCommand(GroupController groupController) {
        this.groupController = groupController;
    }

    @Override
    public boolean matches(String inp) {
        List<String> words=List.of(inp.split(" "));
        return words.size()==4 &&
                words.get(1).equalsIgnoreCase(CommandKeywords.ADD_MEMBER);
    }

    @Override
    public void execute(String inp) {
        List<String> words=List.of(inp.split(" "));
        GroupMemberRequestDto requestDto=new GroupMemberRequestDto();
        requestDto.setMemberId(Integer.parseInt(words.get(3)));
        requestDto.setAdminId(Integer.parseInt(words.get(0)));
        requestDto.setGroupId(Integer.parseInt(words.get(2)));
        GroupMemberResponseDto responseDto=groupController.addMember(requestDto);
        System.out.println(responseDto.getMemberName()+" added to "+responseDto.getGroupName()+" by "+responseDto.getAdminName());
    }
}
