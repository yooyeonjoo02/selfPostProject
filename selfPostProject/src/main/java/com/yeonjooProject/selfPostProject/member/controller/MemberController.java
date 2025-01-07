package com.yeonjooProject.selfPostProject.member.controller;

import com.yeonjooProject.selfPostProject.member.dto.MemberRequestDTO;
import com.yeonjooProject.selfPostProject.member.dto.MemberResponseDTO;
import com.yeonjooProject.selfPostProject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.yeonjooProject.selfPostProject.member.entity.Member;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponseDTO> createMember(@RequestBody MemberRequestDTO requestDTO) {
        Member member = memberService.createMember(requestDTO);
        return ResponseEntity.ok(new MemberResponseDTO(member));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> getMemberById(@PathVariable Long id) {
        Member member = memberService.findMemberById(id);
        return ResponseEntity.ok(new MemberResponseDTO(member));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<MemberResponseDTO> getMemberByUsername(@PathVariable String username) {
        Member member = memberService.findMemberByUsername(username);
        return ResponseEntity.ok(new MemberResponseDTO(member));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> updateMember(@PathVariable Long id, @RequestBody MemberRequestDTO requestDTO) {
        Member member = memberService.updateMember(id, requestDTO);
        return ResponseEntity.ok(new MemberResponseDTO(member));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok("Member deleted successfully");
    }
}
