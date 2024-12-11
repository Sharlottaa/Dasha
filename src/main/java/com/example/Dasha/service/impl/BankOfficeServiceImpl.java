package com.example.Dasha.service.impl;

import com.example.Dasha.dto.BankOfficeDTO;
import com.example.Dasha.mapper.BankOfficeMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.example.Dasha.dto.BankOfficeDTO;
import com.example.Dasha.entity.*;
import com.example.Dasha.repository.*;
import com.example.Dasha.service.*;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BankOfficeServiceImpl implements BankOfficeService {

    private final BankOfficeRepository bankOfficeRepository;
    private final BankRepository bankRepository;
    private final BankService bankService;
    private final BankOfficeMapper bankOfficeMapper;
    @Transactional
    @Override
    public BankOfficeDTO createBankOffice(Long bankId, String name, String address, Boolean status,
                                          Boolean canPlaceAtm, Boolean canIssueLoan, Boolean isIssuingMoney,
                                          Boolean isDepositingMoney, Integer rentalCost) {
        BankOffice bankOffice = new BankOffice();
        Bank bank = bankService.getBankById(bankId);
        bank.setCountOffices(bank.getCountOffices() + 1);
        bankOffice.setBank(bank);
        bankOffice.setName(name);
        bankOffice.setAddress(address);
        bankOffice.setStatus(status);
        bankOffice.setCanPlaceAtm(canPlaceAtm);
        bankOffice.setCountOfAtms((int)(Math.random() * bank.getCountAtms()));
        bankOffice.setCanIssueLoan(canIssueLoan);
        bankOffice.setIsIssuingMoney(isIssuingMoney);
        bankOffice.setIsDepositingMoney(isDepositingMoney);
        bankOffice.setAmountOfMoney((int)(Math.random() * bank.getTotalMoney()));
        bankOffice.setRentalCost(rentalCost);
        bankRepository.save(bank);
        bankOfficeRepository.save(bankOffice);
        return bankOfficeMapper.toDto(bankOffice);
    }
    @Transactional
    @Override
    public BankOffice getBankOfficeById(Long id) {
        return bankOfficeRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Bank Office not found with id " + id));
    }
    @Transactional
    @Override
    public BankOfficeDTO getBankOfficeByIdDto(Long id) {
        return bankOfficeMapper.toDto(getBankOfficeById(id));
    }
    @Transactional
    @Override
    public BankOfficeDTO updateBankOffice(Long id, Long bankId, String name, String address, Boolean status,
                                          Boolean canPlaceAtm, Boolean canIssueLoan, Boolean isIssuingMoney,
                                          Boolean isDepositingMoney, Integer rentalCost) {
        BankOffice bankOffice = getBankOfficeById(id);
        bankOffice.setBank(bankService.getBankById(bankId));
        bankOffice.setName(name);
        bankOffice.setAddress(address);
        bankOffice.setStatus(status);
        bankOffice.setCanPlaceAtm(canPlaceAtm);
        bankOffice.setCanIssueLoan(canIssueLoan);
        bankOffice.setIsIssuingMoney(isIssuingMoney);
        bankOffice.setIsDepositingMoney(isDepositingMoney);
        bankOffice.setRentalCost(rentalCost);
        bankOfficeRepository.save(bankOffice);
        return bankOfficeMapper.toDto(bankOffice);
    }
    @Transactional
    @Override
    public void deleteBankOffice(Long id) {
        BankOffice bankOffice = getBankOfficeById(id);
        Bank bank = bankOffice.getBank();
        bank.setCountOffices(bank.getCountOffices() - 1);
        bankRepository.save(bank);
        bankOfficeRepository.deleteById(id);
    }
}