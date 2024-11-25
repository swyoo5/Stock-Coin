package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_id")
    private Long assetId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AssetType assetType;

    private String assetName;

    @Column(length = 20, nullable = false)
    private String ticker;

//    private LocalDateTime regdate;
}
