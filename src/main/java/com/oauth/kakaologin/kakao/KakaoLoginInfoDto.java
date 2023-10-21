package com.oauth.kakaologin.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KakaoLoginInfoDto {
    private Long id;
    private String nickname;
    private String email;
    private String profileImgUrl;

    public KakaoLoginInfoDto(String nickname, String email, String profileImgUrl) {
        this.nickname = nickname;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
    }
}
