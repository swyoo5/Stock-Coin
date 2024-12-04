package com.example.demo.controlller;

import com.example.demo.dto.UserAssetDTO;
import com.example.demo.entity.UserAsset;
import com.example.demo.service.GetAccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class AccountController {
    private final GetAccountsService getAccountsService;

    @GetMapping("/accounts")
    public String getAccounts(Model model) {
        List<UserAssetDTO> accounts = getAccountsService.getAccounts();
        model.addAttribute("accounts", accounts);
        return "/user/accounts";
    }
}
