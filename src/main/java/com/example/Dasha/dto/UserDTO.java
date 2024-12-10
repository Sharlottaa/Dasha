package com.example.Dasha.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    private String fullName;

    private LocalDate birthDate;

    private String workplace;

    private Integer monthlyIncome;

    private List<String> bankNames;

    private List<Long> creditAccountsId;

    private List<Long> paymentAccountsId;

    private Integer creditRating;
}