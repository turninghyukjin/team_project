package com.project.sul.entity;

import com.project.sul.constant.Role;
import com.project.sul.dto.MemberFormDto;

import com.project.sul.dto.RegisterSocialFormDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Member extends BaseEntity {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String nickname;

    private String email;

    private String password;

    private String phone;

    private Integer age;

    private String impUid; // 추가된 필드: 인증 토큰

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

//    @CreationTimestamp  //자동으로 만들어준다
//    private Timestamp createTime;

    private Integer point;
    //결제 포인트

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "kakao_id") // member 테이블의 컬럼 이름이 "kakao_id"인 것을 가정합니다.
//    private Kakao kakao;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Role role;
    //관리자 아이디

    private String provider;    // oauth2를 이용할 경우 어떤 플랫폼을 이용하는지
    private String providerId;  // oauth2를 이용할 경우 아이디값

    // 생성자, getter, setter 등 필요한 메서드는 생략

    public static Member createMember(RegisterSocialFormDto registerSocialFormDto,
                                      PasswordEncoder passwordEncoder
//                                      String email,
//                                      String impUid,
//                                      LocalDate birthDate, String provider, String providerId) {
                                      ) {
        Member member = new Member();

        member.setName(registerSocialFormDto.getName()); // 이름
        member.setNickname(registerSocialFormDto.getNickname()); // 닉네임
//        member.setEmail(email); // 이메일
        member.setAddress(new Address(registerSocialFormDto.getZipCode(), registerSocialFormDto.getStreetAdr(), registerSocialFormDto.getDetailAdr())); // 주소
        member.setPhone(registerSocialFormDto.getPhone()); // 폰 번호

        String password = passwordEncoder.encode(registerSocialFormDto.getPassword());
        member.setPassword(password);

        member.setRole(Role.USER);

//        member.setBirthDate(birthDate); // 생년월일 설정

        // 추가 정보 저장
//        member.setImpUid(impUid); // 인증 토큰

        // oauth2
//        member.setProvider(provider);
//        member.setProviderId(providerId);

        // Kakao 정보 저장
        // Kakao kakaoDto = registerSocialFormDto.getKakao();
        // if (kakaoDto != null) {
        //     member.setKakao(kakaoDto);
        // }

        return member;
    }

    @Builder(builderClassName = "UserDetailRegister", builderMethodName = "userDetailRegister")
    public Member(String username, String password, String email, Role role) {
        this.name = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Builder(builderClassName = "OAuth2Register", builderMethodName = "oauth2Register")
    public Member(String nickname, String password, String email, Role role, String provider, String providerId) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }
}
