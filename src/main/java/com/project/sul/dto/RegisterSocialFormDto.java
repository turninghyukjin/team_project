package com.project.sul.dto;

import com.project.sul.entity.Address;
import com.project.sul.entity.Member;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegisterSocialFormDto {

    @NotBlank(message = "이름을 입력해 주세요.")
    private String name;

    @NotBlank(message = "닉네임을 입력해 주세요.")
    @Size(min = 2, max = 10)
    private String nickname;

    @NotEmpty(message = "이메일을 입력해 주세요.")
    @Email(message = "이메일 형식으로 입력해 주세요.")
    private String email;

    @NotEmpty(message = "비밀번호를 입력해 주세요.")
    @Size(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password;

//    @NotEmpty
//    private String matchingPassword;

    @NotBlank(message = "휴대폰 번호를 입력해 주세요")
    private String phone;

    @NotEmpty
    private String zipCode;

    @NotEmpty
    private String streetAdr;

    @NotEmpty(message = "상세 주소를 입력해 주세요.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-]{2,20}$")
    private String detailAdr;

    private LocalDate birthDate;

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Member toMember() {
        Member member = new Member();
        member.setName(this.name);
        member.setNickname(this.nickname);
        member.setEmail(this.email);
        member.setPassword(this.password);
        member.setPhone(this.phone);
        member.setAddress(new Address(this.zipCode, this.streetAdr, this.detailAdr));
        member.setBirthDate(this.birthDate);

        // Kakao 정보 저장
//        if (kakao != null) {
//            member.setKakao(this.kakao);
//        }

        return member;
    }
}
