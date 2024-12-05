package com.example.demo.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.UUID;

// 업비트 API에 인증하기 위한 토큰 생성
public class OpenApiSample {
    public static void main(String[] args) {
        String accessKey = System.getenv("UPBIT_OPENAPI_ACCESSKEY");
        String secretKey = System.getenv("UPBIT_OPENAPI_SECRETKEY");

        // JWT 서명에 사용할 algorithm 객체
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        // 새로운 JWT 토큰 생성
        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey) // JWT 페이로드에 클레임 추가
                .withClaim("nonce", UUID.randomUUID().toString()) // 클레임 추가, 고유한 UUID를 생성하여 문자열로 변환한 값을 설정
                .sign(algorithm);

        // 인증에 사용할 토큰
        String authenticationToken = "Bearer " + jwtToken;
    }
}
