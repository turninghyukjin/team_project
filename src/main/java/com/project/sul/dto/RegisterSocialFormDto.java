package com.project.sul.dto;

import com.project.sul.entity.Address;
import com.project.sul.entity.Member;


import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class RegisterSocialFormDto {

    @Null
    private Long id;

    @NotBlank(message = "이름을 입력해 주세요.")
    private String name;

    @NotBlank(message = "닉네임을 입력해 주세요.")
    @Size(min = 2, max = 10, message = "닉네임은 2자 이상, 5자 이하로 입력해 주세요.")
    private String nickname;

    @NotEmpty(message = "이메일을 입력해 주세요.")
    @Email
    private String email;

    @NotEmpty(message = "비밀번호를 입력해 주세요.")
    @Size(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해 주세요.")
    private String password;

//    @NotEmpty
//    private String matchingPassword;

    @Null
    private String phone;

    private String zipCode;

    private String streetAdr;

    @NotEmpty(message = "상세 주소를 입력해 주세요.")
    private String detailAdr;

//    private LocalDate birthDate;

//    public LocalDate getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(LocalDate birthDate) {
//        this.birthDate = birthDate;
//    }

    public Member toMember() {
        Member member = new Member();
        member.setName(this.name);
        member.setNickname(this.nickname);
        member.setEmail(this.email);
        member.setPassword(this.password);
//        member.setPhone(this.phone);
        member.setAddress(new Address(this.zipCode, this.streetAdr, this.detailAdr));
//        member.setBirthDate(this.birthDate);

        return member;
    }

    public static RegisterSocialFormDto toMember(Member member) {

        RegisterSocialFormDto formDto = new RegisterSocialFormDto();

        formDto.setId(member.getId());
        formDto.setName(member.getName());
        formDto.setNickname(member.getNickname());
        formDto.setEmail(member.getEmail());
        formDto.setPassword(member.getPassword());
        formDto.setPhone(member.getPhone());
        formDto.setZipCode(member.getAddress().getZipCode());
        formDto.setStreetAdr(member.getAddress().getStreetAdr());
        formDto.setDetailAdr(member.getAddress().getDetailAdr());

        return formDto;
    }



}
