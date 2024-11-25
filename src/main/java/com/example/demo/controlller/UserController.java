package com.example.demo.controlller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String loginId,
                        @RequestParam String password,
                        @RequestParam String password1,
                        @RequestParam String email,
                        @RequestParam String nickname,
                         Model model) {
        if (!password.equals(password1)) {
            model.addAttribute("passwordError", "비밀번호가 일치하지 않습니다.");
            return "user/signup";
        }

        if (userService.existsByLoginId(loginId)) {
            model.addAttribute("idError", "이미 존재하는 아이디입니다");
            return "user/signup";
        }

        User user = new User();
        user.setLoginId(loginId);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setEmail(email);

        userService.registerUser(user);
        return "redirect:/user/login";
//        if (userService.existsByLoginId(user.getLoginId())) {
//            model.addAttribute("errorMessage", "이미 존재하는 아이디입니다");
//            return "user/signup";
//        }
//
//        userService.registerUser(user);
//        return "redirect:/user/login";
    }
}
