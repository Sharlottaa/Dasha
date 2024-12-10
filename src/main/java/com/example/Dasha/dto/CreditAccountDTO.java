package com.example.Dasha.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditAccountDTO {
    private Long id;

    private String userName;

    private String bankName;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer numberOfMonths;

    private Integer loanAmount;

    private Integer monthlyPayment;

    private Float interestRate;

    private String issuingEmployeeName;

    private Integer paymentAccountAmount;
}
