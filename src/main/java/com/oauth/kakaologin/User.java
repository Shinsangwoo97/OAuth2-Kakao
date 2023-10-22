package com.oauth.kakaologin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username; //email 아이디

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String userImage;

    @Column(nullable = false)
    private Long kakaoId;

    @Column(nullable = false)
    private LocalDateTime userLastLogin; // 마지막 접속 시간

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime userJoinDate;  // 가입 날짜

    public User(String username, String nickname, String userImage, Long kakaoId, LocalDateTime userLastLogin, LocalDateTime userJoinDate) {
        this.username = username;
        this.nickname = nickname;
        this.userImage = userImage;
        this.kakaoId = kakaoId;
        this.userLastLogin = userLastLogin;
        this.userJoinDate = userJoinDate;
    }
}
