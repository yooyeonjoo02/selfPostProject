package com.yeonjooProject.selfPostProject.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.yeonjooProject.selfPostProject.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
}
