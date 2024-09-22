package com.example.vacationcalculator.service.impl;

import com.example.vacationcalculator.exception.VacationException;
import java.time.LocalDate;

public interface VacationService {
    double calculateVacationPay(double averageSalary, int vacationDaysCount, LocalDate vacationStartDate)
            throws VacationException;
}
