package com.oauth.kakaologin.kakao;

import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoUserService {

    @Value("${REST_API_KEY}")
    private String REST_API_KEY;
    @Value("${REDIRECT_URL}")
    private String REDIRECT_URL;


    public void kakaoLogin(String code, HttpServletResponse response) {
        String accessToken = getAccessToken(code);
    }

    private String getAccessToken(String code) {

        // #1 - 헤더에 Content-type 지정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // #2 - 바디에 필요한 정보 담기
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", REST_API_KEY);
        params.add("redirect_uri", REDIRECT_URL);
        params.add("code", code);
//        params.add("client_secret", "authorization_code"); 필수는 아니지만 보안강화 할떄 필요함

        // #3 - POST 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenReg = new HttpEntity<>(params, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenReg,
                String.class
        );

        // #4 - response에서 엑세스토큰 가져오기
        String tokenJson = response.getBody();
        JSONObject jsonObject = new JSONObject(tokenJson);
        String accessToken = jsonObject.getString("access_token");
        System.out.println("accessToken : " + accessToken);
        return accessToken;
    }
}
