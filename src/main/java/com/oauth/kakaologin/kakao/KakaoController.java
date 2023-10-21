package com.oauth.kakaologin.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KakaoController {
    private final KakaoUserService kakaoUserService;
///oauth/kakao
    @ResponseBody
    @GetMapping("/oauth/kakao")
    public ResponseEntity kakaoCallback(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        String response1 = "성공적으로 카카오 로그인 API 코드를 불러왔습니다.";
        System.out.println("코드 : " + code + " 리스폰스 :" + response);
        kakaoUserService.kakaoLogin(code, response);
        return  new ResponseEntity(response1, HttpStatus.OK);
    }
}
