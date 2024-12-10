package com.example.Dasha.service;

import com.example.Dasha.dto.CreditAccountDTO;
import com.example.Dasha.entity.CreditAccount;

import java.time.LocalDate;

public interface CreditAccountService {

    CreditAccountDTO createCreditAccount(Long userId, Long bankId, LocalDate startDate, LocalDate endDate,
                                         Integer loanAmount, Float interestRate, Long issuingEmployeeId,
                                         Long paymentAccountId);

    CreditAccount getCreditAccountById(Long id);

    CreditAccountDTO getCreditAccountByIdDto(Long id);

    CreditAccountDTO updateCreditAccount(Long id, Long paymentAccountId);

    void deleteCreditAccount(Long id);
}