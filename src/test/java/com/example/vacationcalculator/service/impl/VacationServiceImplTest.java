package com.example.vacationcalculator.service.impl;

import com.example.vacationcalculator.exception.VacationException;
import com.example.vacationcalculator.service.impl.impl.VacationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

class VacationServiceImplTest {

    private final VacationServiceImpl vacationService = new VacationServiceImpl();

    @Test
    void calculateVacationPay_shouldThrowException_whenAverageSalaryIsNegative() {
        Assertions.assertThrows(VacationException.class, () ->
                vacationService.calculateVacationPay(-1000, 10, LocalDate.now()));
    }

    @Test
    void calculateVacationPay_shouldThrowException_whenVacationDaysCountIsNegative() {
        Assertions.assertThrows(VacationException.class, () ->
                vacationService.calculateVacationPay(1000, -10, LocalDate.now()));
    }

    @ParameterizedTest
    @MethodSource("calculateVacationPay_testCases")
    void calculateVacationPay_shouldCalculateCorrectly(double averageSalary, int vacationDaysCount,
                                                       LocalDate vacationStartDate, double expectedResult) throws VacationException {
        double result = vacationService.calculateVacationPay(averageSalary, vacationDaysCount, vacationStartDate);
        Assertions.assertEquals(expectedResult, result, 0.01);
    }

    @org.jetbrains.annotations.NotNull
    private static Stream<Arguments> calculateVacationPay_testCases() {
        return Stream.of(
                Arguments.of(1000, 10, null, 341.297),
                Arguments.of(1000, 10, LocalDate.of(2024, 9, 23), 273.0375),
                Arguments.of(2000, 20, null, 1365.188),
                Arguments.of(2000, 100, LocalDate.of(2024, 9, 1), 4846.416)
        );
    }
}
