package com.example.Dasha.config;


import com.example.Dasha.dto.*;
import com.example.Dasha.entity.*;
import com.example.Dasha.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BankService bankService;
    private final BankOfficeService bankOfficeService;
    private final BankAtmService bankAtmService;
    private final EmployeeService employeeService;
    private final UserService userService;
    private final PaymentAccountService paymentAccountService;
    private final CreditAccountService creditAccountService;

    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        // Инициализация 5 банков
        List<BankDTO> banks = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            BankDTO bank = bankService.createBank("Bank_" + i);
            banks.add(bank);
        }

        // Для каждого банка создаем офиса, банкоматы, сотрудников и клиентов
        for (BankDTO bank : banks) {
            System.out.println("Создание данных для " + bank.getName());

            // Создание 3 банковских офисов
            List<BankOfficeDTO> offices = new ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                BankOfficeDTO office = bankOfficeService.createBankOffice(
                        bank.getId(),
                        bank.getName() + "_Office_" + i,
                        "Address_" + bank.getId() + "_" + i,
                        true, // статус работает
                        true, // можно разместить банкомат
                        true, // можно оформить кредит
                        true, // работает на выдачу денег
                        true, // можно внести деньги
                        1000 + random.nextInt(9001) // стоимость аренды от 1000 до 10000
                );
                offices.add(office);
            }

            // Создание 3 банкоматов для банка
            List<BankAtmDTO> atms = new ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                // Выбираем случайный офис для размещения банкомата
                BankOfficeDTO office = offices.get(random.nextInt(offices.size()));
                // Создаем сотрудника для обслуживания банкомата
                EmployeeDTO employee = createEmployeeForAtm(bank.getId(), office.getId(), "ATM_Employee_" + i);

                BankAtmDTO atm = bankAtmService.createBankAtm(
                        bank.getName() + "_ATM_" + i,
                        office.getAddress(),
                        true, // статус работает
                        bank.getId(),
                        office.getId(),
                        employee.getId(),
                        true, // работает на выдачу денег
                        true, // можно внести деньги
                        500 + random.nextInt(501) // стоимость обслуживания от 500 до 1000
                );
                atms.add(atm);
            }

            // Создание 5 клиентов для банка
            List<UserDTO> clients = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                UserDTO user = userService.createUser(
                        bank.getName() + "_Client_" + i,
                        LocalDate.of(1990 + random.nextInt(30), 1 + random.nextInt(12), 1 + random.nextInt(28)),
                        "Company_" + i
                );

                // Создание 2 платежных счетов для клиента
                for (int j = 1; j <= 2; j++) {
                    paymentAccountService.createPaymentAccount(user.getId(), bank.getId());
                }

                // Создание 2 кредитных счетов для клиента
                for (int j = 1; j <= 2; j++) {
                    // Выбираем случайного сотрудника, который выдаст кредит
                    EmployeeDTO issuingEmployee = getRandomEmployeeFromBank(bank.getId());
                    // Выбираем первый платежный счет клиента для погашения кредита
                    List<PaymentAccountDTO> paymentAccounts = paymentAccountService.getPaymentAccountsByUserId(user.getId());
                    if (paymentAccounts.isEmpty()) {
                        throw new RuntimeException("У пользователя нет платежных счетов для погашения кредита.");
                    }
                    PaymentAccountDTO paymentAccount = paymentAccounts.get(0);

                    creditAccountService.createCreditAccount(
                            user.getId(),
                            bank.getId(),
                            LocalDate.now(),
                            LocalDate.now().plusMonths(12 + random.nextInt(48)), // срок кредита от 12 до 60 месяцев
                            10000 + random.nextInt(90001), // сумма кредита от 10,000 до 100,000
                            bank.getInterestRate(),
                            issuingEmployee.getId(),
                            paymentAccount.getId()
                    );
                }

                clients.add(user);
            }

            // Вывод данных банка в консоль
            printBankData(bank, offices, atms, clients);
        }

        System.out.println("Инициализация данных завершена.");
    }

    private EmployeeDTO createEmployeeForAtm(Long bankId, Long bankOfficeId, String fullName) {
        return employeeService.createEmployee(
                fullName,
                LocalDate.of(1980 + random.nextInt(30), 1 + random.nextInt(12), 1 + random.nextInt(28)),
                "ServiceEmployee",
                bankId,
                false, // не удаленный
                bankOfficeId,
                false, // не может выдавать кредиты
                3000 + random.nextInt(7001) // зарплата от 3000 до 10000
        );
    }

    private EmployeeDTO getRandomEmployeeFromBank(Long bankId) {
        // Предполагается, что у вас есть метод для получения всех сотрудников банка
        // Если такого метода нет, его нужно реализовать в EmployeeService
        List<EmployeeDTO> allEmployees = employeeService.getAllEmployeesByBankId(bankId);
        if (allEmployees.isEmpty()) {
            throw new RuntimeException("Нет сотрудников в банке с ID " + bankId);
        }
        return allEmployees.get(random.nextInt(allEmployees.size()));
    }

    private void printBankData(BankDTO bank, List<BankOfficeDTO> offices, List<BankAtmDTO> atms, List<UserDTO> clients) {
        System.out.println("========================================");
        System.out.println("Банк: " + bank.getName());
        System.out.println("ID: " + bank.getId());
        System.out.println("Количество офисов: " + bank.getCountOffices());
        System.out.println("Количество банкоматов: " + bank.getCountAtms());
        System.out.println("Количество сотрудников: " + bank.getCountEmployees());
        System.out.println("Количество клиентов: " + bank.getCountClients());
        System.out.println("Рейтинг: " + bank.getRating());
        System.out.println("Всего денег в банке: " + bank.getTotalMoney());
        System.out.println("Процентная ставка: " + bank.getInterestRate() + "%");
        System.out.println("------ Офисы ------");
        for (BankOfficeDTO office : offices) {
            System.out.println("Офис: " + office.getName() + ", Адрес: " + office.getAddress());
        }
        System.out.println("------ Банкоматы ------");
        for (BankAtmDTO atm : atms) {
            System.out.println("Банкомат: " + atm.getName() + ", Адрес: " + atm.getAddress() +
                    ", Статус: " + (atm.getStatus() ? "Работает" : "Не работает") +
                    ", Обслуживающий сотрудник: " + atm.getServicingEmployeeName());
        }
        System.out.println("------ Клиенты ------");
        for (UserDTO user : clients) {
            System.out.println("Клиент: " + user.getFullName() + ", ID: " + user.getId());
            printUserAccounts(user);
        }
        System.out.println("========================================\n");
    }

    private void printUserAccounts(UserDTO user) {
        System.out.println("  -- Платежные счета --");
        List<PaymentAccountDTO> paymentAccounts = paymentAccountService.getPaymentAccountsByUserId(user.getId());
        for (PaymentAccountDTO account : paymentAccounts) {
            System.out.println("    Счет ID: " + account.getId() + ", Сумма: " + account.getAmount());
        }

        System.out.println("  -- Кредитные счета --");
        List<CreditAccountDTO> creditAccounts = creditAccountService.getCreditAccountsByUserId(user.getId());
        for (CreditAccountDTO account : creditAccounts) {
            System.out.println("    Кредитный счет ID: " + account.getId() +
                    ", Сумма кредита: " + account.getLoanAmount() +
                    ", Процентная ставка: " + account.getInterestRate() + "%" +
                    ", Ежемесячный платеж: " + account.getMonthlyPayment());
        }
    }
}

