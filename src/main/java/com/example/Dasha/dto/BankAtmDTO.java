package com.example.Dasha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAtmDTO {
    private Long id;

    private String name;

    private String address;

    private Boolean status;

    private String bankName;

    private String locationAddress;

    private String servicingEmployeeName;

    private Boolean isIssuingMoney;

    private Boolean isDepositingMoney;

    private Integer amountOfMoney;

    private Integer servicingCost;
}