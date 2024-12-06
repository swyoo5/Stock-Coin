package com.example.demo.dto;

import com.example.demo.entity.Asset;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAssetDTO {
    @JsonProperty("currency")
    private String currency; // 자산 종류

    @JsonProperty("balance")
    private float balance;

    @JsonProperty("avg_buy_price")
    private float avg_buy_price;

    @JsonProperty("unit_currency")
    private String unit_currency; // 기준화폐
}
