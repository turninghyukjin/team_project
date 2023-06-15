package com.project.sul.kako;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@Slf4j
public class KakaoController {

    private final KakaoService kakaoService;

    @GetMapping("/kakao")
    public String kakaoLogin(@RequestParam(required = false) String code, Model model) {
        try {
            // URL에 포함된 code를 이용하여 액세스 토큰 발급
            String accessToken = kakaoService.getAccessToken(code);
            System.out.println(accessToken);

            // 액세스 토큰을 이용하여 카카오 서버에서 유저 정보(닉네임, 이메일) 받아오기
            HashMap<String, Object> userInfo = kakaoService.getUserInfo(accessToken);
            System.out.println("login Controller : " + userInfo);

            // 회원 가입 페이지로 이동하는 로직
            model.addAttribute("kakaoEmail", userInfo.get("email"));
            return "redirect:/register/final"; // 회원가입 페이지로 리다이렉트
        } catch (Exception e) {
            // 예외 처리 로직
            return "error";
        }
    }

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
            return "redirect:/register/final"; // 리디렉션을 "/register"로 변경
        } else {
            // 로그인 페이지로 이동하는 로직
            return "login";
        }
    }

    @RequestMapping("/register")
    public String register(Model model) {
        // 카카오 로그인을 통해 가져온 이메일 값을 모델에 추가
        String kakaoEmail = ""; // 카카오로부터 이메일 값을 가져와서 할당
        model.addAttribute("kakaoEmail", kakaoEmail);
        return "register";
    }

    @GetMapping("/error")
    public String handleError() {
        // 오류 처리 로직 작성
        return "error";
    }

}
