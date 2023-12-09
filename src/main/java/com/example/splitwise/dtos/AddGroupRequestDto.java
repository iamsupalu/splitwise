package com.example.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddGroupRequestDto {
    private int adminId;
    private String name;
}
