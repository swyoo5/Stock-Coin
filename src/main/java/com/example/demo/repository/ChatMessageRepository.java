package com.example.demo.repository;

import com.example.demo.entity.ChatMessage;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    @Query("SELECT m FROM ChatMessage m WHERE m.chatRoom.name = :chatRoomName ORDER BY m.sentDate ASC")
    List<ChatMessage> findByChatRoomName(@Param("chatRoomName") String chatRoomName);
}
