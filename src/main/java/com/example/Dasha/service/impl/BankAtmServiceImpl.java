package com.example.Dasha.service.impl;

import com.example.Dasha.mapper.BankAtmMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.example.Dasha.dto.BankAtmDTO;
import com.example.Dasha.entity.*;
import com.example.Dasha.repository.*;
import com.example.Dasha.service.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankAtmServiceImpl implements BankAtmService {

    private final BankAtmRepository bankAtmRepository;
    private final BankRepository bankRepository;
    private final BankService bankService;
    private final BankOfficeService bankOfficeService;
    private final EmployeeService employeeService;
    private final BankAtmMapper bankAtmMapper;
    @Transactional
    @Override
    public BankAtmDTO createBankAtm(String name, String address, Boolean status, Long bankId,
                                    Long bankOfficeId, Long employeeId, Boolean isIssuingMoney,
                                    Boolean isDepositingMoney, Integer servicingCost) {
        BankAtm bankAtm = new BankAtm();
        bankAtm.setName(name);
        bankAtm.setAddress(address);
        bankAtm.setStatus(status);
        Bank bank = bankService.getBankById(bankId);
        bank.setCountAtms(bank.getCountAtms() + 1);
        bankAtm.setBank(bank);
        bankAtm.setLocation(bankOfficeService.getBankOfficeById(bankOfficeId));
        bankAtm.setServicingEmployee(employeeService.getEmployeeById(employeeId));
        bankAtm.setIsIssuingMoney(isIssuingMoney);
        bankAtm.setIsDepositingMoney(isDepositingMoney);
        bankAtm.setAmountOfMoney((int)(Math.random() * bank.getTotalMoney()));
        bankAtm.setServicingCost(servicingCost);
        bankRepository.save(bank);
        bankAtmRepository.save(bankAtm);
        return bankAtmMapper.toDto(bankAtm);
    }
    @Transactional
    @Override
    public BankAtm getBankAtmById(Long id) {
        return bankAtmRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Bank Atm not found with id " + id));
    }

    public BankAtmDTO getBankAtmByIdDto(Long id) {
        return bankAtmMapper.toDto(getBankAtmById(id));
    }

    @Transactional
    @Override
    public BankAtmDTO updateBankAtm(Long id, String name, String address, Boolean status, Long bankId,
                                    Long bankOfficeId, Long employeeId, Boolean isIssuingMoney,
                                    Boolean isDepositingMoney, Integer servicingCost) {
        BankAtm bankAtm = getBankAtmById(id);
        bankAtm.setName(name);
        bankAtm.setAddress(address);
        bankAtm.setStatus(status);
        Bank bank = bankService.getBankById(bankId);
        bankAtm.setBank(bank);
        bankAtm.setLocation(bankOfficeService.getBankOfficeById(bankOfficeId));
        bankAtm.setServicingEmployee(employeeService.getEmployeeById(employeeId));
        bankAtm.setIsIssuingMoney(isIssuingMoney);
        bankAtm.setIsDepositingMoney(isDepositingMoney);
        bankAtm.setServicingCost(servicingCost);
        bankAtmRepository.save(bankAtm);
        return bankAtmMapper.toDto(bankAtm);
    }
    @Transactional
    @Override
    public void deleteBankAtm(Long id) {
        BankAtm bankAtm = getBankAtmById(id);
        Bank bank = bankAtm.getBank();
        bank.setCountAtms(bank.getCountAtms() - 1);
        bankRepository.save(bank);
        bankAtmRepository.deleteById(id);
    }
}