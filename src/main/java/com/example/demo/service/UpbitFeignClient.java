package com.example.demo.service;

import com.example.demo.entity.UserAsset;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
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
}
