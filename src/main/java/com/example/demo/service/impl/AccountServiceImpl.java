package com.example.demo.service.impl;

import com.example.demo.mapper.AccountMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.ApiResponseDto;
import com.example.demo.model.entity.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;
import com.example.demo.util.CommonConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Override
    public ApiResponseDto addAccount(HttpServletRequest request, AccountDto accountDto) {
        if (accountRepository.existsById(accountDto.getUsername())) {
            return ApiResponseDto.builder()
                    .code(String.format(HttpStatus.OK.toString()))
                    .message("User already exists")
                    .data(null)
                    .status(CommonConstants.ApiStatus.STATUS_USER_ALREADY_EXISTS)
                    .build();
        }
        Account account = AccountMapper.getInstance().toEntity(accountDto);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return ApiResponseDto.builder()
                .code(String.format(HttpStatus.OK.toString()))
                .message("Created successfully")
                .data(AccountMapper.getInstance().toDto(account))
                .status(CommonConstants.ApiStatus.STATUS_OK)
                .build();

    }
}
