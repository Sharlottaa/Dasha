
package com.example.Dasha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentAccountDTO {
    private Long id;

    private Long userId;

    private Long bankId;

    private Integer amount;
}