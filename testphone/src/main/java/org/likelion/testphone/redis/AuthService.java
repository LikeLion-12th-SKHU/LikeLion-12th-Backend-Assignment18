package org.likelion.testphone.redis;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.likelion.testphone.coolSms.CoolSmsService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CoolSmsService coolSmsService; // SMS 전송 서비스
    private final RedisTemplate<String, String> redisTemplate; // Redis 템플릿

    public String sendAuthCode(String phoneNumber) throws Exception {
        String authCode = generateRandomNumber(); // 랜덤 인증코드 생성
        String message = "인증번호는 [" + authCode + "] 입니다."; // SMS 내용

        // SMS 전송
        coolSmsService.sendSms(phoneNumber, message);

        // Redis에 인증번호 저장 (유효기간 5분)
        redisTemplate.opsForValue().set(phoneNumber, authCode, 5, TimeUnit.MINUTES);

        return authCode;
    }

    public boolean validateAuthCode(String phoneNumber, String authCode) {
        // redis에서 저장된 인즏코드 조회
        String storedCode = redisTemplate.opsForValue().get(phoneNumber);

        // 저장된 코드와 입력된 코드 비교
        return storedCode != null && storedCode.equals(authCode);
    }

    private String generateRandomNumber() {
        Random rand = new Random();
        StringBuilder numStr = new StringBuilder();
        for (int i = 0; i < 4; i++) { // 4자리 인증 번호
            numStr.append(rand.nextInt(10));
        }
        return numStr.toString();
    }


}
