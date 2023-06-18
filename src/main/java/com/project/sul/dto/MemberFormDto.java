//package com.project.sul.dto;
//
//import com.project.sul.entity.Address;
//import com.project.sul.entity.Member;
//import com.project.sul.kako.Kakao;
//
//import lombok.*;
//
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;
//import java.time.LocalDate;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
//public class MemberFormDto {
//
//    private Kakao kakao; // Kakao와의 연결을 위한 필드 추가
//
//    private String name;//
//
//    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
//    private String nickname;
//
//    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
//    @Email(message = "이메일 형식으로 입력해주세요.")
//    private String email;
//
//    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
//    @Size(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
//    private String password;
//
//    @NotEmpty(message = "동일한 비밀번호를 입력해주세요")
//    @Size(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
//    private String matchingPassword;
//
//    @NotBlank(message = "휴대폰 번호를 입력해주세요")
//    private String phone;
//
//    @NotEmpty(message = "주소를 입력해 주세요.")
//    private Address address;
//
//    private LocalDate birthDate;
//
//    public LocalDate getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(LocalDate birthDate) {
//        this.birthDate = birthDate;
//    }
//
//    public Member toMember() {
//        Member member = new Member();
//        member.setName(this.name);
//        member.setNickname(this.nickname);
//        member.setEmail(this.email);
//        member.setPassword(this.password);
//        member.setPhone(this.phone);
//        member.setAddress(this.address);
//        member.setBirthDate(this.birthDate);
//
//        // Kakao 정보 저장
////        if (kakao != null) {
////            member.setKakao(this.kakao);
////        }
//
//        return member;
//    }
//}
