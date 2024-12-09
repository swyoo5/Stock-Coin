package com.example.demo.controlller;

import com.example.demo.service.TickerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TickerController {
    private final TickerService tickerService;

//    @GetMapping("/ticker")
//    public Map<String, Object>[] getTicker(@RequestParam String market) throws IOException {
//        if (market == null || market.isEmpty() || !market.startsWith("KRW-")) {
//            throw new IllegalArgumentException("Invalid market parameter" + market);
//        }
//        System.out.println("market : " + market);
//        return tickerService.getTicker(market);
//    }

    @GetMapping("/ticker")
    public List<Map<String, Object>> getUpbitTickerPrice(@RequestParam String ticker) {
//        System.out.println("Received ticker request : " + ticker);
        return tickerService.getUpbitPrice(ticker);
    }
}
