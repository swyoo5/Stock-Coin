package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(String loginId,
                             String password,
                             String email,
                             String nickname) {
        User user = new User();
        String encodedPassword = passwordEncoder.encode(password);
        user.setLoginId(loginId);
        user.setPassword(encodedPassword);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setRole(Role.ROLE_USER);

        if (user.getRole() == null) {
            user.setRole(Role.ROLE_USER);
        }
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByLoginId(String LoginId) {
        return userRepository.findByLoginId(LoginId);
    }

    @Override
    public boolean existsByLoginId(String LoginId) {
        return userRepository.existsByLoginId(LoginId);
    }
}
