package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// BoardLike 테이블은 복합키를 가지므로 복합키를 하나의 클래스로 다시 만들어줌
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class ChatParticipantId implements Serializable {
    @Column(name="chatroom_id")
    private long chatRoomId;
    @Column(name="user_id")
    private long userId;
}
