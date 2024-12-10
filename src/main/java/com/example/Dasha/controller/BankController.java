package com.example.Dasha.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import com.example.Dasha.dto.BankDTO;
import com.example.Dasha.entity.Bank;
import com.example.Dasha.service.BankService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank")
public class BankController {

    private final BankService bankService;

    // создание банка
    @Operation(summary = "Создание банка")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Банк создан",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankDTO.class))})
    })

    @PostMapping
    public BankDTO createBank(@Parameter(description = "Название банка") @RequestParam("name") String name) {
        return bankService.createBank(name);
    }

    // поиск банка по ID
    @Operation(summary = "Поиск банка по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Банк найден",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankDTO.class))})
    })

    @GetMapping("/{id}")
    public BankDTO getBankById(@Parameter(description = "ID банка") @PathVariable("id") Long id) {
        return bankService.getBankByIdDto(id);
    }

    // изменение данных в банке
    @Operation(summary = "Изменение данных в банке по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные изменены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankDTO.class))})
    })

    @PatchMapping("/{id}")
    public BankDTO updateBank(@Parameter(description = "ID банка") @PathVariable("id") Long id,
                              @RequestParam("name") String name) {
        return bankService.updateBank(id, name);
    }

    // удаление банка
    @Operation(summary = "Удаление банка")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Банк удален",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BankDTO.class))})
    })

    @DeleteMapping("/{id}")
    public Bank deleteBank(@Parameter(description = "ID банка") @PathVariable("id") Long id) {
        bankService.deleteBank(id);
        return null;
    }

}