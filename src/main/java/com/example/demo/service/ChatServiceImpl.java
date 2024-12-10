package com.example.demo.service;

import com.example.demo.dto.ChatMessageDTO;
import com.example.demo.entity.ChatMessage;
import com.example.demo.entity.Chatroom;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.ChatMessageRepository;
import com.example.demo.repository.ChatRoomRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.logging.LoggingRebinder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final LoggingRebinder loggingRebinder;

    @Transactional
    public ChatMessage saveChatMessage(ChatMessageDTO dto) {
        // ticker로 채팅방 조회, 없으면 생성
        Chatroom chatroom = chatRoomRepository.findByName(dto.getTicker())
                .orElseGet(() -> {
                    Chatroom newRoom = Chatroom.builder()
                            .name(dto.getTicker())
                            .build();
                    System.out.println("New room: " + newRoom.getName());
                    return chatRoomRepository.save(newRoom);
                });

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("You are not authenticated");
        }
        System.out.println("ChatService Authentication : " + authentication);
        String loginId = authentication.getName();
        // sender로 user 조회, 없으면 생성
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException(("로그인한 사용자를 찾을 수 없습니다")));
//        User user = userRepository.findByNickname(loginId)
//                .orElseGet(() -> {
//                    User newUser = User.builder()
//                            .nickname(dto.getSender())
//                            .loginId(loginId)
//                            .password("tempPassword")
//                            .email("tempEmail@example.com")
//                            .role(Role.ROLE_USER)
//                            .build();
//                    return userRepository.save(newUser);
//                });

        System.out.println("Sender : " + user.getNickname());
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoom(chatroom)
                .sender(user)
                .content(dto.getContent())
                .build();

        System.out.println("saved chatmessage : " + chatMessage);
        return chatMessageRepository.save(chatMessage);
    }
}
