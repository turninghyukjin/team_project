package com.project.sul.repository;

import com.project.sul.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);

    Member findByNickname(String nickname);

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);

    // 임의데이터
    @Modifying
    @Query(value = "INSERT INTO member (name, nickname, email, password, phone, age, role) " +
            "VALUES ('admin', '관리자', 'admin@naver.com', '123456789', '01012341234', 20, ADMIN)", nativeQuery = true)
    void addAdmin();

//    Optional<Member> findByMemberEmail(String email);

}