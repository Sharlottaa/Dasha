package com.example.Dasha.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDTO {

    private Long id;

    private String name;

    private Integer countOffices;

    private Integer countAtms;

    private Integer countEmployees;

    private Integer countClients;

    private Integer rating;

    private Integer totalMoney;

    private Float interestRate;
}