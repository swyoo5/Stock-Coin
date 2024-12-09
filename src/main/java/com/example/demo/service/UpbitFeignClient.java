package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ObjectInputFilter;
import java.util.List;
import java.util.Map;


@FeignClient(name = "UpbitFeignClient", url = "https://api.upbit.com")
public interface UpbitFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/v1/ticker")
    List<Map<String, Object>> getUpBitPriceList(@RequestParam(value="markets") String markets);

    @RequestMapping(method = RequestMethod.GET, value = "/v1/candles/{timeframe}")
    List<Map<String, Object>> getCandleData(
            @RequestParam("market") String market,
            @PathVariable("timeframe") String timeframe,
            @RequestParam("count") int count
    );
}
