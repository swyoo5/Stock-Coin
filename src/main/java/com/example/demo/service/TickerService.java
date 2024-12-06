package com.example.demo.service;

import com.example.demo.entity.UserAsset;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TickerService {
    public List<Map<String, Object>> getUpbitPrice(String markets);
}
