package com.example.demo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.dto.CustomErrorResponse;
import com.example.demo.dto.UserAssetDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.servlet.View;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class GetAccountsServiceImpl implements GetAccountsService {
    private final String accessKey = System.getenv("UPBIT_OPENAPI_ACCESSKEY");
    private final String secretKey = System.getenv("UPBIT_OPENAPI_SECRETKEY");
    private final String serverUrl = System.getenv("UPBIT_OPENAPI_SERVERURL");
    private final View error;

    public GetAccountsServiceImpl(View error) {
        this.error = error;
    }

    public List<UserAssetDTO> getAccounts() {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String jwtToken = JWT.create()
                    .withClaim("access_key", accessKey)
                    .withClaim("nonce", UUID.randomUUID().toString())
                    .sign(algorithm);
            String authenticationToken = "Bearer " + jwtToken;

            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/v1/accounts");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String jsonResponse = EntityUtils.toString(entity, "UTF-8");

            System.out.println("API Response : " + jsonResponse);

            ObjectMapper objectMapper = new ObjectMapper();
            if (jsonResponse.startsWith("{")) {
                CustomErrorResponse errorResponse = objectMapper.readValue(jsonResponse, CustomErrorResponse.class);
                System.err.println("Error Response : " + errorResponse);
                throw new RuntimeException();
            } else if (jsonResponse.startsWith("[")) {
                return objectMapper.readValue(jsonResponse, new TypeReference<List<UserAssetDTO>>() {});
            } else {
                throw new RuntimeException("Unexpected API Response");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get accounts");
        }
    }
}
