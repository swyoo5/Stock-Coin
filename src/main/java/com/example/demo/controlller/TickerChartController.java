package com.example.demo.controlller;

import com.example.demo.service.TickerChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TickerChartController {
    private final TickerChartService tickerChartService;

    @GetMapping("/chart")
    public List<Map<String, Object>> getDailyChartData(@RequestParam String ticker,
                                                       @RequestParam(required = false, defaultValue = "30") int count) {
        System.out.println("Fetching daily chart data for" + ticker);
        return tickerChartService.getDailyChartData(ticker, count);
    }
}
