package com.example.Dasha.dto;

import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;

    private String fullName;

    private LocalDate birthDate;

    private String position;

    private String bankName;

    private Boolean isRemote;

    private String officeName;

    private Boolean canIssueLoans;

    private Integer salary;
}