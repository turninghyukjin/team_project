package com.project.sul.auth;

import com.project.sul.auth.userInfo.KakaoUserInfo;
import com.project.sul.auth.userInfo.OAuth2UserInfo;
import com.project.sul.constant.Role;
import com.project.sul.entity.Member;
import com.project.sul.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
//    참고 사이트 : https://lotuus.tistory.com/104

    @Autowired
    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getRegistrationId();

        if (provider.equals("kakao")) {
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
//        } else if (provider.equals("naver")) {
//            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
//        } else if (provider.equals("google")) {
//            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttribute());
        }

        String providerId = oAuth2UserInfo.getProviderId();

//        String username = provider + "_" + providerId;
        String nickname = oAuth2UserInfo.getNickName();
        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String password = passwordEncoder.encode("패스워드" + uuid);

        String email = oAuth2UserInfo.getEmail();
        Role role = Role.USER;

        Member byMemberEmail = memberRepository.findByEmail(email);

        //DB에 없는 사용자라면 회원가입처리
        if(byMemberEmail == null){
            byMemberEmail = Member.oauth2Register()
                    .nickname(nickname).password(password).email(email).role(role)
                    .provider(provider).providerId(providerId)
                    .build();
            memberRepository.save(byMemberEmail);
        }

        return new PrincipalDetails(byMemberEmail, (Map<String, Object>) oAuth2UserInfo);
    }
}
