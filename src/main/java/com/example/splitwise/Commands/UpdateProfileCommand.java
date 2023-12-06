package com.example.splitwise.Commands;

import java.util.List;

public class UpdateProfileCommand implements Command{

    @Override
    public boolean matches(String inp) {
        List<String> words=List.of(inp.split(" "));
        return words.size()==3 &&
                words.get(0).equalsIgnoreCase(CommandKeywords.UPDATE_PROFILE);
    }

    @Override
    public void execute(String inp) {
        List<String> words=List.of(inp.split(" "));

    }
}
