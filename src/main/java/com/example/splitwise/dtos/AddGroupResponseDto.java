package com.example.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddGroupResponseDto {
    private int id;
    private String name;
    private String admin;
    private String errorMessage;
}
