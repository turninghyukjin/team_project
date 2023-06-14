package com.project.sul.kako;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@Slf4j
public class KakaoController {

    private final KakaoService kakaoService;

    @RequestMapping("/")
    public String login(@RequestParam(value = "code", required = false) String code) throws Exception {
        if (code != null) {
            // 인증 코드(code)를 처리하는 로직
            String access_Token = kakaoService.getAccessToken(code);
            HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
            System.out.println("#########" + code);
            System.out.println("###access_Token#### : " + access_Token);
            System.out.println("###userInfo#### : " + userInfo.get("email"));
            System.out.println("###nickname#### : " + userInfo.get("nickname"));
            return "redirect:/register/agreement"; // 리디렉션을 "/register"로 변경
        } else {
            // 로그인 페이지로 이동하는 로직
            return "login";
        }
    }

    @RequestMapping("/register")
    public String register() {
        // 회원가입 페이지로 이동하는 로직
        return "register";
    }
}
