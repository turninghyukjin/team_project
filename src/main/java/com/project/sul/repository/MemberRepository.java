package com.project.sul.repository;

import com.project.sul.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    //이메일
    Member findByEmail(String email);

    //닉네임
    Member findByNickname(String nickname);


}
