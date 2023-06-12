package com.project.sul.service;

import com.project.sul.dto.MemberFormDto;
import com.project.sul.entity.Address;
import com.project.sul.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Member createMember() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test@email.com");

        memberFormDto.setPassword("1234");

//        Address address = new Address();
//        address.setCity("서울시");
//
//
//        address.setStreet("합정동");


        return Member.createMember(memberFormDto,passwordEncoder);
    }
    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest(){
        Member member = createMember();
        Member saveMember = memberService.saveMember(member);

        assertEquals(member.getEmail(),saveMember.getEmail());
        assertEquals(member.getName(),saveMember.getName());
        assertEquals(member.getNickname(),saveMember.getNickname());

        assertEquals(member.getRole(),saveMember.getRole());
    }



    @Test
    @DisplayName("중복 회원 가입 테스트")
    public void saveDuplicateMemberTest() {
        // 회원1 생성
        Member member1 = createMember();

        // ...

        // 회원2 생성
        Member member2 = createMember();


        // 회원1 저장
        memberService.saveMember(member1);

        // 중복 회원 가입 시 예외 발생 여부 확인
        Throwable e = assertThrows(IllegalStateException.class, () -> {
            memberService.saveMember(member2);
        });

        // 예상되는 예외 메시지 확인
        assertEquals("이미 가입된 회원입니다.", e.getMessage());
    }



}
