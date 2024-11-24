package org.likelion.testphone.redis;

import lombok.RequiredArgsConstructor;
import org.likelion.testphone.coolSms.CoolSmsService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CoolSmsService coolSmsService;
    private final RedisTemplate<String, String> redisTemplate;

    public String sendAuthCode(String phoneNumber) throws Exception {
        String authCode = generateRandomNumber();
        String message = "인증 번호는 [" + authCode + "입니다.";

        coolSmsService.sendSms(phoneNumber, message);

        redisTemplate.opsForValue().set(phoneNumber, authCode, 5, TimeUnit.MINUTES);

        return authCode;
    }

    public boolean validateAuthCode(String phoneNumber, String authCode) {
        String storedCode = redisTemplate.opsForValue().get(phoneNumber);
        return storedCode != null && storedCode.equals(authCode);
    }

    private String generateRandomNumber() {
        Random rand = new Random();
        StringBuilder numStr = new StringBuilder();
        for(int i = 0; i < 4; i++) {
            numStr.append(rand.nextInt(10));
        }
        return numStr.toString();
    }


}
