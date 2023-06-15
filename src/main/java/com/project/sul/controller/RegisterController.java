package com.project.sul.controller;

import com.project.sul.dto.RegisterDto;
import com.project.sul.entity.Member;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @PostMapping("/certifications")
    public void handleCertificationRequest(@RequestBody Map<String, String> request) {
        String impUid = request.get("imp_uid");

        try {
            // 인증 토큰 발급 받기
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, String> tokenRequest = new HashMap<>();
            tokenRequest.put("imp_key", "1570777386811537"); // REST API키
            tokenRequest.put("imp_secret", "2k16RFAZ05kKlaM60SdMzmUobtT2KeeeDCeypvUdq07UjS4Bd2N9ZHz1lyK1h397iscygpegNn4bAxhz"); // REST API Secret
            ResponseEntity<Map> tokenResponseEntity = restTemplate.postForEntity(
                    "https://api.iamport.kr/users/getToken",
                    tokenRequest,
                    Map.class
            );
            String accessToken = (String) tokenResponseEntity.getBody().get("access_token");

            // imp_uid로 인증 정보 조회
            headers.setBearerAuth(accessToken);
            ResponseEntity<Map> getCertificationsResponseEntity = restTemplate.getForEntity(
                    "https://api.iamport.kr/certifications/" + impUid,
                    Map.class,
                    headers
            );
            Map<String, Object> getCertificationsResponse = getCertificationsResponseEntity.getBody();
            Map<String, Object> certificationsInfo = (Map<String, Object>) getCertificationsResponse.get("response");
            // 처리 로직 작성
            // ...
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/certification")
    public void handleCertificationRequest(@RequestBody RegisterDto registerDto) {
        String impUid = registerDto.getImpUid();
        // 처리 로직 작성
    }

    @PostMapping
    public void handleRegisterRequest(@RequestBody RegisterDto registerDto) {
        try {
            String impUid = registerDto.getImpUid();

            // 서버에서 필요한 인증 정보 확인 및 로직 수행
            // ...



            // 인증 정보를 받았을 때의 처리
            if (isAgeValid(registerDto)) {
                // 만 19세 이상인 경우에만 인증 허용
                // 성공적인 처리 로직 작성

                // 이름 저장
                String name = registerDto.getName();
                Member member = new Member();
                member.setName(name);


                System.out.println("인증 성공");
            } else {
                // 인증 실패 처리 로직 작성
                System.out.println("인증 실패: 만 19세 미만");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 에러 발생 시 예외 처리 로직 작성
            System.out.println("인증 실패: 에러 발생");
        }
    }

    private boolean isAgeValid(RegisterDto registerDto) {
        // 연령 검증 로직 구현
        // 예시: 현재 날짜와 생년월일을 비교하여 만 19세 이상인지 확인
        LocalDate now = LocalDate.now();
        LocalDate birthDate = registerDto.getBirthDate();
        int age = Period.between(birthDate, now).getYears();
        return age >= 19;
    }


}
