package com.example.demo.dto;

import lombok.Data;

@Data
public class ChatMessageDTO {
    private String ticker;
    private String sender;
    private String content;
}
