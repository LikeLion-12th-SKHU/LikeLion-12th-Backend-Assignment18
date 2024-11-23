package com.likelion.likeliontestphone.redis;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class AuthCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 데이터베이스 id
    private String phoneNumber; // 전화번호
    private String authCode; // 인증번호

    // 전화번호와 인증 코드를 매개변수로 받는 생성자
    public AuthCode(String phoneNumber, String authCode) {
        this.phoneNumber = phoneNumber; // 전화번호 초기화
        this.authCode = authCode; // 인증 코드 초기화
    }
}
