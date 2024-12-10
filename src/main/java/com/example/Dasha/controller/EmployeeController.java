package com.example.Dasha.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import com.example.Dasha.dto.EmployeeDTO;
import com.example.Dasha.entity.Employee;
import com.example.Dasha.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    // создание сотрудника
    @Operation(summary = "Создание сотрудника")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Сотрудник создан",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDTO.class))})
    })

    @PostMapping
    public EmployeeDTO createEmployee(@RequestParam("fullName") String fullName,
                                      @RequestParam("birthDate") LocalDate birthDate,
                                      @RequestParam("position") String position,
                                      @RequestParam("bankId") Long bankId,
                                      @RequestParam("isRemote") Boolean isRemote,
                                      @RequestParam("bankOfficeId") Long bankOfficeId,
                                      @RequestParam("canIssueLoans") Boolean canIssueLoans,
                                      @RequestParam("salary") Integer salary) {
        return employeeService.createEmployee(fullName, birthDate, position, bankId, isRemote,
                bankOfficeId, canIssueLoans, salary);
    }

    // поиск сотрудника по ID
    @Operation(summary = "Поиск сотрудника по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "сотрудник найден",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDTO.class))})
    })

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@Parameter(description = "ID сотрудника") @PathVariable("id") Long id) {
        return employeeService.getEmployeeByIdDto(id);
    }

    // изменение данных сотрудника
    @Operation(summary = "Изменение данных сотрудника по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные изменены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDTO.class))})
    })

    @PatchMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable("id") Long id, @RequestParam("fullName") String fullName,
                                      @RequestParam("position") String position,
                                      @RequestParam("bankId") Long bankId,
                                      @RequestParam("isRemote") Boolean isRemote,
                                      @RequestParam("bankOfficeId") Long bankOfficeId,
                                      @RequestParam("canIssueLoans") Boolean canIssueLoans,
                                      @RequestParam("salary") Integer salary) {
        return employeeService.updateEmployee(id, fullName, position, bankId, isRemote,
                bankOfficeId, canIssueLoans, salary);
    }

    // удаление сотрудника
    @Operation(summary = "Удаление сотрудника")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "сотрудник удален",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDTO.class))})
    })

    @DeleteMapping("/{id}")
    public Employee deleteEmployee(@Parameter(description = "ID сотрудника") @PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return null;
    }
}