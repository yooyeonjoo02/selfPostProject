package com.yeonjooProject.selfPostProject.member.dto;

import lombok.Data;
import com.yeonjooProject.selfPostProject.member.entity.Member;

@Data
public class MemberResponseDTO {
    private Long id;
    private String username;
    private String role;

    public MemberResponseDTO(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.role = member.getRole();
    }
}
