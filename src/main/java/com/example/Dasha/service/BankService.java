package com.example.Dasha.service;

import com.example.Dasha.dto.BankDTO;
import com.example.Dasha.entity.Bank;

public interface BankService {

    BankDTO createBank(String bankName);

    Bank getBankById(Long id);

    BankDTO getBankByIdDto(Long id);

    BankDTO updateBank(Long id, String name);

    void deleteBank(Long id);
}