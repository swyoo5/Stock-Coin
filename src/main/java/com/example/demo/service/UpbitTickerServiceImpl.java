package com.example.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

// 종목 현재가
@Service
public class UpbitTickerServiceImpl implements UpbitTickerService {
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, Object>[] getTicker(String market) throws IOException {
        Request request = new Request.Builder()
                .url("https://api.upbit.com/v1/ticker?markets=" + market)
                .get()
                .addHeader("accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                return objectMapper.readValue(response.body().string(), Map[].class);
            }
            throw new RuntimeException("Failed to fetch ticker data");
        }
    }
}
