package com.yeonjooProject.selfPostProject.member.dto;

import lombok.Data;

@Data
public class MemberRequestDTO {
    private String username;
    private String password;
    private String role; // USER 또는 ADMIN
}
