package com.example.demo.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.UUID;

public class OpenApiSample {
    public static void main(String[] args) {
        String accessKey = System.getenv("UPBIT_OPENAPI_ACCESSKEY");
        String secretKey = System.getenv("UPBIT_OPENAPI_SECRETKEY");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;
    }
}
