package com.yeonjooProject.selfPostProject.member.service;

import com.yeonjooProject.selfPostProject.member.dto.MemberRequestDTO;
import com.yeonjooProject.selfPostProject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yeonjooProject.selfPostProject.member.entity.Member;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member createMember(MemberRequestDTO requestDTO) {
        // 중복 검사
        Optional<Member> existingMember = memberRepository.findByUsername(requestDTO.getUsername());
        if (existingMember.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // Member 생성 및 저장
        Member member = Member.builder()
                .username(requestDTO.getUsername())
                .password(requestDTO.getPassword())
                .role(requestDTO.getRole())
                .build();

        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    @Transactional(readOnly = true)
    public Member findMemberByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    @Transactional
    public Member updateMember(Long id, MemberRequestDTO requestDTO) {
        Member existingMember = findMemberById(id);

        Member updatedMember = Member.builder()
                .id(existingMember.getId()) // 기존 ID 유지
                .username(existingMember.getUsername()) // 기존 사용자명 유지
                .password(requestDTO.getPassword()) // 새로운 비밀번호 설정
                .role(requestDTO.getRole()) // 새로운 역할 설정
                .posts(existingMember.getPosts()) // 기존 게시물 유지
                .comments(existingMember.getComments()) // 기존 댓글 유지
                .build();

        return memberRepository.save(updatedMember);
    }

    @Transactional
    public void deleteMember(Long id) {
        Member member = findMemberById(id);
        memberRepository.delete(member);
    }
}
