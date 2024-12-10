package com.example.Dasha.service;

import com.example.Dasha.dto.BankAtmDTO;
import com.example.Dasha.entity.BankAtm;

public interface BankAtmService {

    BankAtmDTO createBankAtm(String name, String address, Boolean status, Long bankId,
                             Long bankOfficeId, Long employeeId, Boolean isIssuingMoney,
                             Boolean isDepositingMoney, Integer servicingCost);
    BankAtm getBankAtmById(Long id);

    BankAtmDTO getBankAtmByIdDto(Long id);
    BankAtmDTO updateBankAtm(Long id, String name, String address, Boolean status, Long bankId,
                             Long bankOfficeId, Long employeeId, Boolean isIssuingMoney,
                             Boolean isDepositingMoney, Integer servicingCost);

    void deleteBankAtm(Long id);
}