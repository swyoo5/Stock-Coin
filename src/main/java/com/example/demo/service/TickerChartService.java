package com.example.demo.service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;


public interface TickerChartService {
    public List<Map<String, Object>> getDailyChartData(String ticker, int count);
}
