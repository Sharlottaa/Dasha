package com.example.Dasha.mapper;


import com.example.Dasha.dto.BankAtmDTO;
import com.example.Dasha.entity.BankAtm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BankAtmMapper {

    @Mapping(source = "location.address", target = "locationAddress")
    @Mapping(source = "servicingEmployee.fullName", target = "servicingEmployeeName")
    @Mapping(source = "bank.name", target = "bankName")
    BankAtmDTO toDto(BankAtm bankAtm);
}

