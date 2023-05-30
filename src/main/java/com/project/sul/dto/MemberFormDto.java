package com.project.sul.dto;

import com.project.sul.entity.Address;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberFormDto {

//    @NotBlank(message = "이름은 필수 입력 값입니다.")
//    private String name;

//    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
//    private String nickname;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password;
    private String matchingPassword;

//    @NotBlank(message = "휴대폰 번호를 입력해주세요")
//    private String phone;

//    @NotEmpty(message = "주소를 입력해 주세요.")
//    private Address address;



}
