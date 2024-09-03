package com.example.demo.controller;

import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.ApiResponseDto;
import com.example.demo.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<ApiResponseDto> createAccount (
            HttpServletRequest request,
            @RequestBody AccountDto accountDto
    ) {
        return new ResponseEntity<>(accountService.addAccount(request, accountDto), HttpStatus.CREATED);
    }
}
