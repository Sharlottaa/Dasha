package com.example.Dasha.service;

import com.example.Dasha.dto.BankOfficeDTO;
import com.example.Dasha.entity.BankOffice;

public interface BankOfficeService {
    BankOfficeDTO createBankOffice(Long bankId, String name, String address, Boolean status,
                                   Boolean canPlaceAtm, Boolean canIssueLoan, Boolean isIssuingMoney,
                                   Boolean isDepositingMoney, Integer rentalCost);

    BankOffice getBankOfficeById(Long id);

    BankOfficeDTO getBankOfficeByIdDto(Long id);

    BankOfficeDTO updateBankOffice(Long id, Long bankId, String name, String address, Boolean status,
                                   Boolean canPlaceAtm, Boolean canIssueLoan, Boolean isIssuingMoney,
                                   Boolean isDepositingMoney, Integer rentalCost);

    void deleteBankOffice(Long id);
}