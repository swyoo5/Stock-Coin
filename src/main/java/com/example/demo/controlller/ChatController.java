package com.example.demo.controlller;

import com.example.demo.dto.ChatMessageDTO;
import com.example.demo.service.ChatService;
import com.example.demo.service.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatServiceImpl chatServiceImpl;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessageDTO chatMessageDTO, @Header("simpSessionAttributes") Map<String, Object> sessionAttributes) {
        Authentication authentication = (Authentication) sessionAttributes.get("authentication");

        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("AAAAAAuthentication : " + authentication);
        } else {
            System.out.println("AAAAAAuthentication null");
        }

        chatServiceImpl.saveChatMessage(chatMessageDTO);
        messagingTemplate.convertAndSend("/topic/chat/" + chatMessageDTO.getTicker(), chatMessageDTO);
    }
}
