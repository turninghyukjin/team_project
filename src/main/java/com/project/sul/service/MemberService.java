package com.project.sul.service;

import com.project.sul.dto.RegisterSocialFormDto;
import com.project.sul.entity.Member;
import com.project.sul.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public boolean checkEmailDuplicate(String email) {
        return memberRepository.existsByEmail(email);
    }

    public Boolean checkNicknameDuplicate(String nickname) {
        System.out.println(" 서비스 : 넘어온 닉네임 = " + nickname);
        return memberRepository.existsByNickname(nickname);
    }

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        validateDuplicateNickname(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member existingMember = memberRepository.findByEmail(member.getEmail());
        if (existingMember != null) {
            throw new IllegalStateException(member.getEmail() + "이미 가입된 회원입니다.!!!!");
        }
    }

    private void validateDuplicateNickname(Member member) {
        Member existingMember = memberRepository.findByNickname(member.getNickname());
        if (existingMember != null) {
            throw new IllegalStateException(member.getEmail() + "이미 사용 중인 닉네임입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

//    public void save(MemberDTO memberDTO) {
//        // 1. dto -> entity 변환
//        // 2. repository의 save 메서드 호출
//        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
//        memberRepository.save(memberEntity);
//        // repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
//    }
//
//    public RegisterSocialFormDto login(RegisterSocialFormDto registerSocialFormDto) {
//
//        Optional<Member> byMemberEmail = memberRepository.findByMemberEmail(registerSocialFormDto.getEmail());
//
//        if (byMemberEmail.isPresent()) {
//
//            Member member = byMemberEmail.get();
//
//            if (member.getPassword().equals(registerSocialFormDto.getPassword())) {
//                RegisterSocialFormDto formDto = RegisterSocialFormDto.toMember(member);
//                return formDto;
//            } else {
//                // 비밀번호 불일치(로그인실패)
//                return null;
//            }
//        } else {
//            // 조회 결과가 없다(해당 이메일을 가진 회원이 없다)
//            return null;
//        }
//    }
//
//    public List<MemberDTO> findAll() {
//        List<MemberEntity> memberEntityList = memberRepository.findAll();
//        List<MemberDTO> memberDTOList = new ArrayList<>();
//        for (MemberEntity memberEntity: memberEntityList) {
//            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
////            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
////            memberDTOList.add(memberDTO);
//        }
//        return memberDTOList;
//    }
//
//    public RegisterSocialFormDto findById(Long id) {
//        Optional<Member> memberOptional = memberRepository.findById(id);
//        if (memberOptional.isPresent()) {
//            Member member = memberOptional.get();
//            RegisterSocialFormDto formDto =
//            return RegisterSocialFormDto.
//        } else {
//            return null;
//        }
//
//    }
//
//    public MemberDTO updateForm(String myEmail) {
//        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
//        if (optionalMemberEntity.isPresent()) {
//            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
//        } else {
//            return null;
//        }
//    }
//
//    public void update(MemberDTO memberDTO) {
//        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
//    }
//
//    public void deleteById(Long id) {
//        memberRepository.deleteById(id);
//    }
//
//    public String emailCheck(String memberEmail) {
//        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberEmail);
//        if (byMemberEmail.isPresent()) {
//            // 조회결과가 있다 -> 사용할 수 없다.
//            return null;
//        } else {
//            // 조회결과가 없다 -> 사용할 수 있다.
//            return "ok";
//        }
//    }

}
