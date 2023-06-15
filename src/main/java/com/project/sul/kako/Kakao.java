package com.project.sul.kako;

import com.project.sul.dto.MemberFormDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kakao{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String kakaoId;

    public Kakao(Long id, String nickname, String kakaoId) {
        this.id = id;
        this.nickname = nickname;
        this.kakaoId = kakaoId;
    }

    public MemberFormDto toMemberFormDto() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setName(this.nickname);
        memberFormDto.setNickname(this.nickname);
        memberFormDto.setEmail(this.kakaoId); // 카카오톡 아이디 (이메일) 설정
        memberFormDto.setKakao(this); // KakaoDto 설정
        return memberFormDto;
    }
}