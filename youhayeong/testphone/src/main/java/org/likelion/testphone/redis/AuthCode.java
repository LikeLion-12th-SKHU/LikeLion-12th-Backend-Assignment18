package org.likelion.testphone.redis;


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
    private Long id;
    private String phoneNumber;
    private String authCode;

    public AuthCode(String phoneNumber, String authCode) {
        this.phoneNumber = phoneNumber;
        this.authCode = authCode;
    }
}

