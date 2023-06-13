package com.project.sul.entity;

import com.project.sul.constant.Role;
import com.project.sul.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity{
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String nickname;

    private String email;

    private String password;

    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    private Integer point;
    //결제 포인트

    @Enumerated(EnumType.STRING)
    private Role role;
    //관리자 아이디

    public static Member createMember(MemberFormDto memberFormDto,
                                      PasswordEncoder passwordEncoder){
        Member member = new Member();
//        member.setName(memberFormDto.getName());//이름
//        member.setNickname(memberFormDto.getNickname());//닉네임
        member.setEmail(memberFormDto.getEmail());//이메일
//        member.setAddress(memberFormDto.getAddress());//주소
//        member.setPhone(memberFormDto.getPhone()); // 폰 번호
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        return member;
    }
}
