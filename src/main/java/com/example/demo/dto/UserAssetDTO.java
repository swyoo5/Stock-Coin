package com.example.demo.dto;

import com.example.demo.entity.Asset;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAssetDTO {
    @JsonProperty("currency")
    private Asset asset; // 자산 종류

    @JsonProperty("balance")
    private float quantity;

    @JsonProperty("avg_buy_price")
    private float averagePrice;

    @JsonProperty("unit_currency")
    private String unitCurrency; // 기준화폐
}
