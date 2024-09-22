package com.example.vacationcalculator.controller;

import com.example.vacationcalculator.exception.VacationException;
import com.example.vacationcalculator.service.impl.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/calculate")
public class VacationController {
    private final VacationService vacationService;

    @Autowired
    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping
    public ResponseEntity<Double> calculateVacation(
            @RequestParam double averageSalary,
            @RequestParam int vacationDaysCount,
            @RequestParam(required = false) LocalDate vacationStartDate) throws VacationException {
        double vacationPay = vacationService.calculateVacationPay(averageSalary, vacationDaysCount, vacationStartDate);
        return ResponseEntity.ok(vacationPay);
    }
}