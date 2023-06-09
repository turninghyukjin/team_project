package com.project.sul.dto;

import com.project.sul.entity.Address;
import lombok.*;
import org.springframework.stereotype.Controller;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Controller
@Getter
@Setter
@ToString
public class RegisterSocialFormDto {

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String nickname;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "주소를 입력해 주세요.")
    private Address address;
}
