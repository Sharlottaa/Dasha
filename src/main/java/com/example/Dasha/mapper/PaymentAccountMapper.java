package com.example.Dasha.mapper;

import com.example.Dasha.dto.PaymentAccountDTO;
import com.example.Dasha.entity.PaymentAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentAccountMapper {

    @Mapping(source = "bank.id", target = "bankId")
    @Mapping(source = "user.id", target = "userId")
    PaymentAccountDTO toDto(PaymentAccount paymentAccount);

}