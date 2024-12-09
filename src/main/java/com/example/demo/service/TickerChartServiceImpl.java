package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TickerChartServiceImpl implements TickerChartService {
    private final UpbitFeignClient upbitFeignClient;

    public List<Map<String, Object>> getDailyChartData(String ticker, int count) {
        try {
            return upbitFeignClient.getCandleData(ticker, "days", count);
        } catch (Exception e) {
            System.err.println("Error fetching daily chart data for ticker : " + ticker);
            throw new RuntimeException("Failed to fetch daily chart data" ,e);
        }
    }
}
