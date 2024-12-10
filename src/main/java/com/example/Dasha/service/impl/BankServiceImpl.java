package com.example.Dasha.service.impl;

import com.example.Dasha.mapper.BankMapper;
import lombok.RequiredArgsConstructor;
import com.example.Dasha.dto.BankDTO;
import com.example.Dasha.entity.Bank;
import com.example.Dasha.repository.BankRepository;
import com.example.Dasha.service.BankService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final BankMapper bankMapper;

    @Override
    public BankDTO createBank(String name) {
        Bank bank = new Bank();
        bank.setName(name);
        bank.setCountOffices(0);
        bank.setCountAtms(0);
        bank.setCountEmployees(0);
        bank.setCountClients(0);
        bank.setRating((int)(Math.random() * 101));
        bank.setTotalMoney((int)(Math.random() * 1_000_001));
        bank.setInterestRate((float)(Math.random() * (20 - (bank.getRating() / 5.0)) + 1));
        bankRepository.save(bank);
        return bankMapper.toDto(bank);
    }

    @Override
    public Bank getBankById(Long id) {
        return bankRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank not found with id " + id));
    }

    @Override
    public BankDTO getBankByIdDto(Long id) {
        return bankMapper.toDto(getBankById(id));
    }

    @Override
    public BankDTO updateBank(Long id, String name) {
        Bank bank = getBankById(id);
        bank.setName(name);
        bankRepository.save(bank);
        return bankMapper.toDto(bank);
    }

    @Override
    public void deleteBank(Long id) {
        bankRepository.deleteById(id);
    }
}