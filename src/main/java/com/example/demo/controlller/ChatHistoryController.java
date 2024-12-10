package com.example.demo.controlller;

import com.example.demo.dto.ChatMessageDTO;
import com.example.demo.entity.ChatMessage;
import com.example.demo.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatHistoryController {
    private final ChatMessageRepository chatMessageRepository;

    @GetMapping("/history")
    public List<ChatMessageDTO> getChatHistory(@RequestParam String ticker) {
        List<ChatMessage> chatMessages = chatMessageRepository.findByChatRoomName(ticker);

        return chatMessages.stream()
                .map(message -> new ChatMessageDTO(
                        ticker,
                        message.getSender().getNickname(),
                        message.getContent(),
                        message.getSentDate().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                ))
                .collect(Collectors.toList());
    }
}
