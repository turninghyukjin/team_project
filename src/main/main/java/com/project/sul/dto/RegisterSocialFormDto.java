package com.project.sul.dto;

import com.project.sul.entity.Address;
import lombok.*;
import org.springframework.stereotype.Controller;

import javax.validation.constraints.*;

@Controller
@Getter
@Setter
@ToString
public class RegisterSocialFormDto {

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
//    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$")
    @Size(min = 2, max = 10)
    private String nickname;

    @NotEmpty
    private String email;

    @NotEmpty
    private String zipCode;

    @NotEmpty
    private String streetAdr;

    @NotEmpty(message = "상세 주소를 입력해 주세요.")
    @Pattern(regexp = "\"^[ㄱ-ㅎ가-힣a-z0-9-]{2,20}$")
    private String detailAdr;
}