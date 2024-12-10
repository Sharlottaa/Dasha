package com.example.Dasha.mapper;

import com.example.Dasha.dto.BankDTO;
import com.example.Dasha.dto.EmployeeDTO;
import com.example.Dasha.entity.Bank;
import com.example.Dasha.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "bank.name", target = "bankName")
    @Mapping(source = "office.name", target = "officeName")
    EmployeeDTO toDto(Employee employee);

}