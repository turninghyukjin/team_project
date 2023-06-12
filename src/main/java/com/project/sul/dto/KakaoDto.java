package com.project.sul.dto;

public class KakaoDto {
    private Long id;
    private String nickname;
    private String email;

    public KakaoDto(Long id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }
}
