package com.example.demo.service;

import com.example.demo.entity.UserAsset;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 종목 현재가
@Service
public class TickerServiceImpl implements TickerService {
//    private final OkHttpClient client = new OkHttpClient();
//    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UpbitFeignClient upbitFeignClient;

    @Autowired
    public TickerServiceImpl(UpbitFeignClient upbitFeignClient) {
        this.upbitFeignClient = upbitFeignClient;
    }
//    public Map<String, Object> getTicker(String market) throws IOException {
//        Request request = new Request.Builder()
//                .url("https://api.upbit.com/v1/ticker?markets=" + market)
//                .get()
//                .addHeader("accept", "application/json")
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) {
//                throw new RuntimeException("Upbit API call failed :  " + response.message());
//            }
//            if (response.body() == null) {
//                return objectMapper.readValue(response.body().string(), new TypeReference<Map<String, Object>>() {});
//
////                String jsonResponse = response.body().string();
////                return objectMapper.readValue(jsonResponse, new TypeReference<Map<String, Object>[]>() {});
//            }
//            throw new RuntimeException("Empty response body from Upbit API");
//        }

    public List<Map<String, Object>> getUpbitPrice(String markets) {
        try {
//            System.out.println("Fetching markets : " + markets);
            String[] marketArray = markets.split(",");
            List<Map<String, Object>> results = new ArrayList<>();

            for (String market : marketArray) {
                try {
//                    System.out.println("Fetching upbit price for market : " + market);
                    results.addAll(upbitFeignClient.getUpBitPriceList(market));
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.err.println("Error fetching price for market : " + ", Error : " + e.getMessage());
                }
            }
            return results;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        return upbitFeignClient.getUpBitPriceList(markets);
    }
}
