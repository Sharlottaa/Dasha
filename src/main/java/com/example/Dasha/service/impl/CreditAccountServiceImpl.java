package com.example.Dasha.service.impl;

import com.example.Dasha.mapper.CreditAccountMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.example.Dasha.dto.CreditAccountDTO;
import com.example.Dasha.entity.Bank;
import com.example.Dasha.entity.CreditAccount;
import com.example.Dasha.entity.PaymentAccount;
import com.example.Dasha.entity.User;
import com.example.Dasha.repository.CreditAccountRepository;
import com.example.Dasha.repository.UserRepository;
import com.example.Dasha.service.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditAccountServiceImpl implements CreditAccountService {

    private final CreditAccountRepository creditAccountRepository;
    private final UserService userService;
    private final BankService bankService;
    private final EmployeeService employeeService;
    private final PaymentAccountService paymentAccountService;
    private final UserRepository userRepository;
    private final CreditAccountMapper creditAccountMapper;

    public User addCreditAccountToUser(Long userId, Long creditAccountId) {
        User user = userService.getUserById(userId);
        CreditAccount creditAccount = getCreditAccountById(creditAccountId);
        user.getCreditAccounts().add(creditAccount);
        return userRepository.save(user);
    }
    @Transactional
    @Override
    public CreditAccountDTO createCreditAccount(Long userId, Long bankId, LocalDate startDate, LocalDate endDate,
                                                Integer loanAmount, Float interestRate, Long issuingEmployeeId,
                                                Long paymentAccountId) {
        CreditAccount creditAccount = new CreditAccount();
        creditAccount.setUser(userService.getUserById(userId));
        Bank bank = bankService.getBankById(bankId);
        creditAccount.setBank(bank);
        creditAccount.setStartDate(startDate);
        creditAccount.setEndDate(endDate);
        creditAccount.setNumberOfMonths(endDate.getDayOfMonth() - startDate.getDayOfMonth());
        creditAccount.setLoanAmount(loanAmount);
        if (interestRate <= bank.getInterestRate())
            creditAccount.setInterestRate(interestRate);
        else
            throw new RuntimeException("High interest rate");

        double monthlyRate = interestRate / 12 / 100;
        double monthlyPayment = loanAmount * (monthlyRate / (1 - Math.pow(1 + monthlyRate, creditAccount.getNumberOfMonths())));
        creditAccount.setMonthlyPayment((int)monthlyPayment);
        creditAccount.setIssuingEmployee(employeeService.getEmployeeById(issuingEmployeeId));
        creditAccount.setPaymentAccount(paymentAccountService.getPaymentAccountById(paymentAccountId));
        creditAccountRepository.save(creditAccount);
        addCreditAccountToUser(userId, creditAccount.getId());
        return creditAccountMapper.toDto(creditAccount);
    }
    @Transactional
    @Override
    public CreditAccount getCreditAccountById(Long id) {
        return creditAccountRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Credit account not found with id " + id));
    }
    @Transactional
    @Override
    public CreditAccountDTO getCreditAccountByIdDto(Long id) {
        return creditAccountMapper.toDto(getCreditAccountById(id));
    }
    @Transactional
    @Override
    public CreditAccountDTO updateCreditAccount(Long id, Long paymentAccountId) {
        CreditAccount creditAccount = getCreditAccountById(id);
        creditAccount.setPaymentAccount(paymentAccountService.getPaymentAccountById(paymentAccountId));
        creditAccountRepository.save(creditAccount);
        return creditAccountMapper.toDto(creditAccount);
    }
    @Transactional
    @Override
    public void deleteCreditAccount(Long id) {
        creditAccountRepository.deleteById(id);
    }
    @Transactional
    @Override
    public List<CreditAccountDTO> getCreditAccountsByUserId(Long userId) {
        List<CreditAccount> accounts = creditAccountRepository.findAllByUserId(userId);
        return accounts.stream()
                .map(creditAccountMapper::toDto)
                .collect(Collectors.toList());
    }

}