package com.example.demo.controlller;

import com.example.demo.dto.AssetDTO;
import com.example.demo.dto.UserAssetDTO;
import com.example.demo.entity.Asset;
import com.example.demo.entity.UserAsset;
import com.example.demo.service.GetAccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:3000")
public class AccountController {
    private final GetAccountsService getAccountsService;

    @GetMapping("/accounts")
    public List<UserAssetDTO> getAccounts(Model model) {
//        List<UserAssetDTO> accounts = getAccountsService.getAccounts();
//        model.addAttribute("accounts", accounts);
//        return "/user/accounts";

//        System.out.println("Returned Data : " + getAccountsService.getAccounts());
//        return getAccountsService.getAccounts();

        try {
            List<UserAssetDTO> userAssets = getAccountsService.getAccounts();

            System.out.println("fetched user assets : " + userAssets);

            return userAssets.stream()
                    .map(asset -> UserAssetDTO.builder()
                            .currency(asset.getCurrency())
                            .balance(asset.getBalance())
                            .avg_buy_price(asset.getAvg_buy_price())
                            .unit_currency(asset.getUnit_currency())
                            .build())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("failed to fetch user assets", e);
        }

    }
}
