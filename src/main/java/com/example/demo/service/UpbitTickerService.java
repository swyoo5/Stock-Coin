package com.example.demo.service;

import java.util.Map;

public interface UpbitTickerService {
    public Map<String, Object>[] getTicker(String market);
}
