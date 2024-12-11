package com.example.Dasha.service;

import com.example.Dasha.dto.PaymentAccountDTO;
import com.example.Dasha.entity.PaymentAccount;
import java.util.List;

public interface PaymentAccountService {
    PaymentAccountDTO createPaymentAccount(Long userId, Long bankId);

    PaymentAccount getPaymentAccountById(Long id);

    PaymentAccountDTO getPaymentAccountByIdDto(Long id);

    PaymentAccountDTO updatePaymentAccount(Long id, Integer amount);

    void deletePaymentAccount(Long id);
    List<PaymentAccountDTO> getPaymentAccountsByUserId(Long userId);
}