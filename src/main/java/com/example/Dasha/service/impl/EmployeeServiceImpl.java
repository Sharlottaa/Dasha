package com.example.Dasha.service.impl;
import com.example.Dasha.dto.EmployeeDTO;
import com.example.Dasha.mapper.EmployeeMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.example.Dasha.entity.Bank;
import com.example.Dasha.entity.BankOffice;
import com.example.Dasha.entity.Employee;
import com.example.Dasha.repository.BankRepository;
import com.example.Dasha.repository.EmployeeRepository;
import com.example.Dasha.service.BankOfficeService;
import com.example.Dasha.service.BankService;
import com.example.Dasha.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BankRepository bankRepository;
    private final BankService bankService;
    private final BankOfficeService bankOfficeService;
    private final EmployeeMapper employeeMapper;
    @Transactional
    @Override
    public EmployeeDTO createEmployee(String fullName, LocalDate birthDate, String position, Long bankId,
                                      Boolean isRemote, Long bankOfficeId, Boolean canIssueLoans, Integer salary) {
        Employee employee = new Employee();
        employee.setFullName(fullName);
        employee.setBirthDate(birthDate);
        employee.setPosition(position);
        Bank bank = bankService.getBankById(bankId);
        bank.setCountEmployees(bank.getCountEmployees() + 1);
        employee.setBank(bank);
        employee.setIsRemote(isRemote);
        employee.setOffice(bankOfficeService.getBankOfficeById(bankOfficeId));
        employee.setCanIssueLoans(canIssueLoans);
        employee.setSalary(salary);
        bankRepository.save(bank);
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }
    @Transactional
    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }
    @Transactional
    @Override
    public EmployeeDTO getEmployeeByIdDto(Long id) {
        return employeeMapper.toDto(getEmployeeById(id));
    }
    @Transactional
    @Override
    public EmployeeDTO updateEmployee(Long id, String fullName, String position, Long bankId,
                                      Boolean isRemote, Long bankOfficeId, Boolean canIssueLoans, Integer salary) {
        Employee employee = getEmployeeById(id);
        employee.setFullName(fullName);
        employee.setPosition(position);
        employee.setBank(bankService.getBankById(bankId));
        employee.setIsRemote(isRemote);
        employee.setOffice(bankOfficeService.getBankOfficeById(bankOfficeId));
        employee.setCanIssueLoans(canIssueLoans);
        employee.setSalary(salary);
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }
    @Transactional
    @Override
    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        Bank bank = employee.getBank();
        bank.setCountEmployees(bank.getCountEmployees() - 1);
        bankRepository.save(bank);
        employeeRepository.deleteById(id);
    }
    @Transactional
    @Override
    public List<EmployeeDTO> getAllEmployeesByBankId(Long bankId) {
        List<Employee> employees = employeeRepository.findAllByBankId(bankId);
        return employees.stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

}