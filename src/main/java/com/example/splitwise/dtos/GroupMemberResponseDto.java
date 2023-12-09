package com.example.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupMemberResponseDto {
    private String memberName;
    private String adminName;
    private String groupName;
    private String errorMessage;
}
