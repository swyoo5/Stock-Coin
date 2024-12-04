package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long userId;
    private String loginId;
    private String email;
    private LocalDateTime regDate;
    private String profileImage;
    private String nickname;
}
