package com.example.demo.dto;

import com.example.demo.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDTO {
    private Long userId;
    private String loginId;
    private String email;
    private LocalDateTime regDate;
    private String profileImage;
    private String nickname;
    private String role;
}
