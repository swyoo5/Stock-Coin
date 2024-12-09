package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

import java.util.Optional;

public interface UserService {
    void registerUser(String loginId, String password, String email, String nickname);
    Optional<User> findByLoginId(String LoginId);
    boolean existsByLoginId(String LoginId);
    UserDTO getUserInfo(String loginId);
}
