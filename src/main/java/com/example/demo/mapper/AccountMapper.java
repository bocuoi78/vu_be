package com.example.demo.mapper;

import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.entity.Account;

public class AccountMapper {
    private static AccountMapper INSTANCE;
    public static AccountMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new AccountMapper();
        }
        return INSTANCE;
    }

    public AccountDto toDto (Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername(account.getUsername());
        accountDto.setPassword(account.getPassword());
        accountDto.setFullname(account.getFullname());
        return accountDto;
    }

    public Account toEntity (AccountDto accountDto) {
        Account account = new Account();
        account.setUsername(accountDto.getUsername());
        account.setPassword(accountDto.getPassword());
        account.setFullname(accountDto.getFullname());
        return account;
    }
}
