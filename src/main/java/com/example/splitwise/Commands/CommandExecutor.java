package com.example.splitwise.Commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandExecutor {
    private List<Command> commands;

    @Autowired
    public CommandExecutor(RegisterCommand registerCommand) {
        commands=new ArrayList<>();
        commands.add(registerCommand);
    }

    public void execute(String inp){
        for(Command c:commands){
            if(c.matches(inp)){
                c.execute(inp);
            }
        }
    }
}
