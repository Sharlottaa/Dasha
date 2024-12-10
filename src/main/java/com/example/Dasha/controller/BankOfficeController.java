package com.example.Dasha.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import com.example.Dasha.dto.BankOfficeDTO;
import com.example.Dasha.entity.BankOffice;
import com.example.Dasha.service.BankOfficeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank_office")
public class BankOfficeController {

    private final BankOfficeService bankOfficeService;

    // создание банковского офиса
    @Operation(summary = "Создание банковского офиса")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Банковский офис создан",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankOfficeDTO.class))})
    })

    @PostMapping
    public BankOfficeDTO createBank(@RequestParam("bankId") Long bankId, @RequestParam("name") String name,
                                    @RequestParam("address") String address, @RequestParam("status") Boolean status,
                                    @RequestParam("canPlaceAtm") Boolean canPlaceAtm,
                                    @RequestParam("canIssueLoan") Boolean canIssueLoan,
                                    @RequestParam("isIssuingMoney") Boolean isIssuingMoney,
                                    @RequestParam("isDepositingMoney") Boolean isDepositingMoney,
                                    @RequestParam("rentalCost") Integer rentalCost) {
        return bankOfficeService.createBankOffice(bankId, name, address, status,
                canPlaceAtm, canIssueLoan, isIssuingMoney, isDepositingMoney, rentalCost);
    }

    // поиск банковского офиса по ID
    @Operation(summary = "Поиск банковского офиса по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Банковский офис найден",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankOfficeDTO.class))})
    })

    @GetMapping("/{id}")
    public BankOfficeDTO getBankOfficeById(@Parameter(description = "ID банковского офиса") @PathVariable("id") Long id) {
        return bankOfficeService.getBankOfficeByIdDto(id);
    }

    // изменение данных в банковском офисе
    @Operation(summary = "Изменение данных в банковском офисе по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные изменены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankOfficeDTO.class))})
    })

    @PatchMapping("/{id}")
    public BankOfficeDTO updateBankOffice(@PathVariable("id") Long id, @RequestParam("bankId") Long bankId,
                                          @RequestParam("name") String name, @RequestParam("address") String address,
                                          @RequestParam("status") Boolean status,
                                          @RequestParam("canPlaceAtm") Boolean canPlaceAtm,
                                          @RequestParam("canIssueLoan") Boolean canIssueLoan,
                                          @RequestParam("isIssuingMoney") Boolean isIssuingMoney,
                                          @RequestParam("isDepositingMoney") Boolean isDepositingMoney,
                                          @RequestParam("rentalCost") Integer rentalCost) {
        return bankOfficeService.updateBankOffice(id, bankId, name, address, status,
                canPlaceAtm, canIssueLoan, isIssuingMoney, isDepositingMoney, rentalCost);
    }

    // удаление банковского офиса
    @Operation(summary = "Удаление банковского офиса")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Банковский офис удален",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankOfficeDTO.class))})
    })

    @DeleteMapping("/{id}")
    public BankOffice deleteBank(@Parameter(description = "ID банковского офиса") @PathVariable("id") Long id) {
        bankOfficeService.deleteBankOffice(id);
        return null;
    }

}