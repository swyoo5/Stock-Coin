package com.example.demo.controlller;

import com.example.demo.dto.ChatMessageDTO;
import com.example.demo.service.ChatService;
import com.example.demo.service.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatServiceImpl chatServiceImpl;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessageDTO chatMessageDTO) {
        chatServiceImpl.saveChatMessage(chatMessageDTO);
        messagingTemplate.convertAndSend("/topic/chat/" + chatMessageDTO.getTicker(), chatMessageDTO);
    }
}
