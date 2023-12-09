package com.example.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupMemberRequestDto {
    private int adminId;
    private int groupId;
    private int memberId;
}
