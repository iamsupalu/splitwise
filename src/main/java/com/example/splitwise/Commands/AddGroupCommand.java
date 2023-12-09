package com.example.splitwise.Commands;

import com.example.splitwise.controller.GroupController;
import com.example.splitwise.dtos.AddGroupRequestDto;
import com.example.splitwise.dtos.AddGroupResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddGroupCommand implements Command{
    GroupController groupController;

    public AddGroupCommand(GroupController groupController) {
        this.groupController = groupController;
    }

    @Override
    public boolean matches(String inp) {
        List<String> words=List.of(inp.split(" "));
        return words.size()==3 &&
                words.get(1).equalsIgnoreCase(CommandKeywords.ADD_GROUP);
    }

    @Override
    public void execute(String inp) {
        List<String> words=List.of(inp.split(" "));
        AddGroupRequestDto addGroupRequestDto=new AddGroupRequestDto();
        addGroupRequestDto.setAdminId(Integer.parseInt(words.get(0)));
        addGroupRequestDto.setName(words.get(2));
        AddGroupResponseDto responseDto=groupController.createGroup(addGroupRequestDto);
        if(responseDto.getErrorMessage().isEmpty()) {
            System.out.println(responseDto.getId()+" "+responseDto.getName()+" "+responseDto.getAdmin());
        }
        else{
            System.out.println(responseDto.getErrorMessage());
        }
    }
}
