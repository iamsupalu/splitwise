package com.example.splitwise.Commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandExecutor {
    private List<Command> commands;

    @Autowired
    public CommandExecutor(RegisterCommand registerCommand,
                           UpdateProfileCommand updateProfileCommand,
                           SettleUpCommand settleUpCommand,
                           AddGroupCommand addGroupCommand,
                           AddMemberCommand addMemberCommand) {
        commands=new ArrayList<>();
        commands.add(registerCommand);
        commands.add(updateProfileCommand);
        commands.add(settleUpCommand);
        commands.add(addGroupCommand);
        commands.add(addMemberCommand);
    }

    public void execute(String inp){
        for(Command c:commands){
            if(c.matches(inp)){
                c.execute(inp);
            }
        }
    }
}
