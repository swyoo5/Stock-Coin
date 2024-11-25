package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.Optional;

public interface UserService {
    void registerUser(User user);
    Optional<User> findByLoginId(String LoginId);
    boolean existsByLoginId(String LoginId);
}
