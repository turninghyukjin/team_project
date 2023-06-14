package com.project.sul.kako;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Service
public class KakaoService {

    // 카카오로부터 access_token을 얻어오는 메소드
    public String getAccessToken(String authorize_code) {
        String access_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // HTTP 요청 설정
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setDoOutput(true);

            // HTTP 요청 바디 설정
            String requestBody = "grant_type=authorization_code"
                    + "&client_id=bf28ed31fcb1b73d74523ad7a6c0a869"
                    + "&redirect_uri=http://localhost/login"
                    + "&code=" + authorize_code;

            // HTTP 요청 전송
            OutputStream out = conn.getOutputStream();
            out.write(requestBody.getBytes("UTF-8"));
            out.flush();
            out.close();

            // HTTP 응답 처리
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();

                // JSON 응답에서 access_token 추출
                Gson gson = new Gson();
                JsonElement element = gson.fromJson(response.toString(), JsonElement.class);
                access_Token = element.getAsJsonObject().get("access_token").getAsString();

                System.out.println("access_token: " + access_Token);
            } else {
                System.out.println("HTTP error code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }

    // 카카오로부터 사용자 정보를 가져오는 메소드
    public HashMap<String, Object> getUserInfo(String access_Token) {
        HashMap<String, Object> userInfo = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // HTTP 요청 설정
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            // HTTP 응답 처리
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();

                // JSON 응답에서 이메일 정보 추출
                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(response.toString());
                JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
                String email = kakao_account.getAsJsonObject().get("email").getAsString();

                userInfo.put("email", email);

                System.out.println("email: " + email);
            } else {
                System.out.println("HTTP error code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userInfo;
    }
}
