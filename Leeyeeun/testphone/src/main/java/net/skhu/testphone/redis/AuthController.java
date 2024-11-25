package net.skhu.testphone.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/send")
    public String sendAuthCode(@RequestParam String phoneNumber) {
        try {
            authService.sendAuthCode(phoneNumber);
            return "인증번호가 전송되었습니다."; // 성공 메시지 반환
        } catch (Exception e) {
            return "SMS 전송 실패: " + e.getMessage(); // 오류 메시지 반환
        }
    }

    @PostMapping("/validate")
    public String validateAuthCode(@RequestParam String phoneNumber, @RequestParam String authCode) {
        // 인증 코드 유효성 검사
        boolean isValid = authService.validateAuthCode(phoneNumber, authCode);
        return isValid ? "인증 성공" : "인증 실패"; // 결과 메시지 출력
    }
}