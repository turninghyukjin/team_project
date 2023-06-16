package com.project.sul.service;

import com.project.sul.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final MemberRepository memberRepository;

    public AdminService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public void createAdminAccount() {
        memberRepository.addAdmin();
    }
}
