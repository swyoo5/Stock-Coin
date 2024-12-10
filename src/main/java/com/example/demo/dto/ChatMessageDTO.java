package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ChatMessageDTO {
    private String ticker;
    private String sender;
    private String content;
    private String time;
}
