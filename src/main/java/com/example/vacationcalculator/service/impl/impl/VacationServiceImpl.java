package com.example.vacationcalculator.service.impl.impl;

import com.example.vacationcalculator.exception.VacationException;
import org.springframework.stereotype.Service;
import com.example.vacationcalculator.service.impl.VacationService;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class VacationServiceImpl implements VacationService {

    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;
    private static final DayOfWeek SATURDAY = DayOfWeek.SATURDAY;
    private static final DayOfWeek SUNDAY = DayOfWeek.SUNDAY;

    @Override
    public double calculateVacationPay(double averageSalary, int vacationDaysCount, LocalDate vacationStartDate)
            throws VacationException {
        validateInputs(averageSalary, vacationDaysCount);

        double dailySalary = averageSalary / AVERAGE_DAYS_IN_MONTH;
        int weekdaysCount = (vacationStartDate != null)
                ? countWeekdays(vacationStartDate, vacationDaysCount)
                : vacationDaysCount;

        return dailySalary * weekdaysCount;
    }

    private void validateInputs(double averageSalary, int vacationDaysCount) throws VacationException {
        if (averageSalary < 0) {
            throw new VacationException("Average salary cannot be less than 0.");
        }
        if (vacationDaysCount < 0) {
            throw new VacationException("Number of vacation days cannot be less than 0.");
        }
    }

    private static int countWeekdays(LocalDate startDate, int n) {
        int weekdaysCount = 0;

        for (int i = 0; i < n; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if (dayOfWeek != SATURDAY && dayOfWeek != SUNDAY) {
                weekdaysCount++;
            }
        }

        return weekdaysCount;
    }
}
