package com.example.demo.service;

import java.io.IOException;
import java.util.Map;

public interface UpbitTickerService {
    public Map<String, Object>[] getTicker(String market) throws IOException;
}
