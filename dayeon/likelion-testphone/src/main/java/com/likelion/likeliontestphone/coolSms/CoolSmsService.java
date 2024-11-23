package com.likelion.likeliontestphone.coolSms;

import jakarta.annotation.PostConstruct;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CoolSmsService {

    private DefaultMessageService messageService;

    @Value("${coolsms.api.key}")
    private String apiKey; // Coolsms API 키

    @Value("${coolsms.api.secret}")
    private String apiSecret; // Coolsms API secret

    @Value("${coolsms.api.number}")
    private String from; // 발신번호

    @PostConstruct
    public void init() {
        // 의존성이 주입된 후에 API 초기화
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");
    }

    public String sendSms(String to, String text) throws Exception {
        Message message = new Message();
        message.setFrom(from); // 발신번호
        message.setTo(to); // 수신번호
        message.setText(text); // 메시지 내용

        // 메시지 전송을 위한 메서드 호출
        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        return response.getMessageId(); // 메시지 ID 반환
    }
}