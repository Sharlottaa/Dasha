package com.example.Dasha.service.impl;


import com.example.Dasha.dto.PaymentAccountDTO;
import com.example.Dasha.mapper.PaymentAccountMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.example.Dasha.entity.Bank;
import com.example.Dasha.entity.PaymentAccount;
import com.example.Dasha.entity.User;
import com.example.Dasha.repository.BankRepository;
import com.example.Dasha.repository.PaymentAccountRepository;
import com.example.Dasha.repository.UserRepository;
import com.example.Dasha.service.BankService;
import com.example.Dasha.service.PaymentAccountService;
import com.example.Dasha.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentAccountServiceImpl implements PaymentAccountService {

    private final PaymentAccountRepository paymentAccountRepository;
    private final BankRepository bankRepository;
    private final UserService userServices;
    private final BankService bankService;
    private final UserRepository userRepository;
    private final PaymentAccountMapper paymentAccountMapper;

    public User addBankToUser(Long userId, Long bankId) {
        User user = userServices.getUserById(userId);
        Bank bank = bankService.getBankById(bankId);
        user.getBanks().add(bank);
        return userRepository.save(user);
    }

    public User addPaymentAccountToUser(Long userId, Long paymentAccountId) {
        User user = userServices.getUserById(userId);
        PaymentAccount paymentAccount = getPaymentAccountById(paymentAccountId);
        user.getPaymentAccounts().add(paymentAccount);
        return userRepository.save(user);
    }
    @Transactional
    @Override
    public PaymentAccountDTO createPaymentAccount(Long userId, Long bankId) {
        PaymentAccount paymentAccount = new PaymentAccount();
        paymentAccount.setUser(userServices.getUserById(userId));
        Bank bank = bankService.getBankById(bankId);
        bank.setCountClients(bank.getCountClients() + 1);
        paymentAccount.setBank(bank);
        paymentAccount.setAmount(0);
        addBankToUser(userId, bankId);
        bankRepository.save(bank);
        paymentAccountRepository.save(paymentAccount);
        addPaymentAccountToUser(userId, paymentAccount.getId());
        return paymentAccountMapper.toDto(paymentAccount);
    }
    @Transactional
    @Override
    public PaymentAccount getPaymentAccountById(Long id) {
        return paymentAccountRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Payment account Atm not found with id " + id));
    }
    @Transactional
    @Override
    public PaymentAccountDTO getPaymentAccountByIdDto(Long id) {
        return paymentAccountMapper.toDto(getPaymentAccountById(id));
    }
    @Transactional
    @Override
    public PaymentAccountDTO updatePaymentAccount(Long id, Integer amount) {
        PaymentAccount paymentAccount = getPaymentAccountById(id);
        paymentAccount.setAmount(amount);
        paymentAccountRepository.save(paymentAccount);
        return paymentAccountMapper.toDto(paymentAccount);
    }
    @Transactional
    @Override
    public void deletePaymentAccount(Long id) {
        PaymentAccount paymentAccount = getPaymentAccountById(id);
        Bank bank = paymentAccount.getBank();
        bank.setCountClients(bank.getCountClients() - 1);
        bankRepository.save(bank);
        paymentAccountRepository.deleteById(id);
    }
    @Transactional
    @Override
    public List<PaymentAccountDTO> getPaymentAccountsByUserId(Long userId) {
        List<PaymentAccount> accounts = paymentAccountRepository.findAllByUserId(userId);
        return accounts.stream()
                .map(paymentAccountMapper::toDto)
                .collect(Collectors.toList());
    }

}