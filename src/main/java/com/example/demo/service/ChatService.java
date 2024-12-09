package com.example.demo.service;

import com.example.demo.dto.ChatMessageDTO;
import com.example.demo.entity.ChatMessage;

public interface ChatService {
    ChatMessage saveChatMessage(ChatMessageDTO dto);
}
