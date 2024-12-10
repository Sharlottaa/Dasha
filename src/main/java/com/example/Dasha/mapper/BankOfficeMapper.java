package com.example.Dasha.mapper;


import com.example.Dasha.dto.BankOfficeDTO;
import com.example.Dasha.entity.BankOffice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BankOfficeMapper {

    @Mapping(source = "bank.name", target = "bankName")
    BankOfficeDTO toDto(BankOffice bankOffice);

}