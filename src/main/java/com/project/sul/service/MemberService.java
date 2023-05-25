package com.project.sul.service;



import com.project.sul.entity.Member;
import com.project.sul.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
       // validateDupulicateMember(member);
        return memberRepository.save(member);
    }


}
