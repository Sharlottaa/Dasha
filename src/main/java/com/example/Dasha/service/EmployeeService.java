package com.example.Dasha.service;

import com.example.Dasha.dto.EmployeeDTO;
import com.example.Dasha.entity.Employee;

import java.time.LocalDate;

public interface EmployeeService {
    EmployeeDTO createEmployee(String fullName, LocalDate birthDate, String position, Long bankId,
                               Boolean isRemote, Long bankOfficeId, Boolean canIssueLoans, Integer salary);

    Employee getEmployeeById(Long id);

    EmployeeDTO getEmployeeByIdDto(Long id);

    EmployeeDTO updateEmployee(Long id, String fullName, String position, Long bankId,
                               Boolean isRemote, Long bankOfficeId, Boolean canIssueLoans, Integer salary);

    void deleteEmployee(Long id);
}
