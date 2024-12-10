package com.example.Dasha.mapper;
import com.example.Dasha.dto.CreditAccountDTO;
import com.example.Dasha.entity.CreditAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreditAccountMapper {

    @Mapping(source = "user.fullName", target = "userName")
    @Mapping(source = "bank.name", target = "bankName")
    @Mapping(source = "issuingEmployee.fullName", target = "issuingEmployeeName")
    @Mapping(source = "paymentAccount.amount", target = "paymentAccountAmount")
    CreditAccountDTO toDto(CreditAccount creditAccount);

}