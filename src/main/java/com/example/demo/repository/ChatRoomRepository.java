package com.example.demo.repository;

import com.example.demo.entity.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<Chatroom, Long> {
    Optional<Chatroom> findByName(String name);
}
