package com.example.Dasha.mapper;

import com.example.Dasha.dto.BankDTO;
import com.example.Dasha.entity.Bank;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankMapper {

    BankDTO toDto(Bank bank);

}