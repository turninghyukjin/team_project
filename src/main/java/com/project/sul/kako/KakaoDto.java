package com.project.sul.kako;

import com.project.sul.dto.MemberFormDto;

public class KakaoDto {
    private Long id;
    private String nickname;
    private String kakaoId;

    public KakaoDto(Long id, String nickname, String kakaoId) {
        this.id = id;
        this.nickname = nickname;
        this.kakaoId = kakaoId;
    }

    public MemberFormDto toMemberFormDto() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setName(this.nickname);
        memberFormDto.setNickname(this.nickname);
        memberFormDto.setEmail(this.kakaoId); // 카카오톡 아이디 (이메일) 설정
        return memberFormDto;
    }
}