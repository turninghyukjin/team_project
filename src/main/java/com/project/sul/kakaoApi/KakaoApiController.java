package com.project.sul.kakaoApi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class KakaoApiController {
    /**
     * 카카오 로그인 API
     * [GET] /localhost/login
     * @return BaseResponse<String>
     */
    public final KakaoApiService kakaoApiService;

    @ResponseBody
    @GetMapping(value = "/kakao")
    public String kakaoLogin(@RequestParam(value = "code", required = false) String code, Model model) {
        try {
            // URL에 포함된 code를 이용하여 액세스 토큰 발급
            String accessToken = kakaoApiService.getKakaoAccessToken(code);
            System.out.println(accessToken);

            // 액세스 토큰을 이용하여 카카오 서버에서 유저 정보(닉네임, 이메일) 받아오기
            HashMap<String, Object> userInfo = kakaoApiService.getUserInfo(accessToken);
            System.out.println("login Controller : " + userInfo);

            // 회원 가입 페이지로 이동하는 로직
            model.addAttribute("kakaoEmail", userInfo.get("email"));
            return "redirect:/register/social"; // 회원가입 페이지로 리다이렉트
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
