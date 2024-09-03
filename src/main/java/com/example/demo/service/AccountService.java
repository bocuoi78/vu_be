package com.example.demo.service;

import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.ApiResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface AccountService {
    ApiResponseDto addAccount(HttpServletRequest request, AccountDto accountDto);
}
