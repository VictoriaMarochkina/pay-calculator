package ru.marochkina.paycalculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;
import ru.marochkina.paycalculator.model.VacationData;

@Service
public class VacationService {

    private static final BigDecimal AVERAGE_DAYS_IN_MONTH = new BigDecimal("29.3");
    private static final Set<LocalDate> holidays = new HashSet<>();

    static {
        // Добавьте все праздничные дни для 2023 и 2024 годов
        holidays.add(LocalDate.of(2023, 1, 1));
        holidays.add(LocalDate.of(2023, 1, 2));
        holidays.add(LocalDate.of(2023, 1, 3));
        holidays.add(LocalDate.of(2023, 1, 4));
        holidays.add(LocalDate.of(2023, 1, 5));
        holidays.add(LocalDate.of(2023, 1, 6));
        holidays.add(LocalDate.of(2023, 1, 7));
        holidays.add(LocalDate.of(2023, 2, 23));
        holidays.add(LocalDate.of(2023, 3, 8));
        holidays.add(LocalDate.of(2023, 5, 1));
        holidays.add(LocalDate.of(2023, 5, 9));
        holidays.add(LocalDate.of(2023, 6, 12));
        holidays.add(LocalDate.of(2023, 11, 4));

        holidays.add(LocalDate.of(2024, 1, 1));
        holidays.add(LocalDate.of(2024, 1, 2));
        holidays.add(LocalDate.of(2024, 1, 3));
        holidays.add(LocalDate.of(2024, 1, 4));
        holidays.add(LocalDate.of(2024, 1, 5));
        holidays.add(LocalDate.of(2024, 1, 6));
        holidays.add(LocalDate.of(2024, 1, 7));
        holidays.add(LocalDate.of(2024, 2, 23));
        holidays.add(LocalDate.of(2024, 3, 8));
        holidays.add(LocalDate.of(2024, 5, 1));
        holidays.add(LocalDate.of(2024, 5, 9));
        holidays.add(LocalDate.of(2024, 6, 12));
        holidays.add(LocalDate.of(2024, 11, 4));
    }

    public BigDecimal calculateVacationPay(VacationData request) {
        int vacationDays = calculateVacationDays(request);

        BigDecimal averageSalary = request.getAverageSalary();

        BigDecimal dailySalary = averageSalary.divide(AVERAGE_DAYS_IN_MONTH, 10, RoundingMode.HALF_UP);

        BigDecimal vacationPay = dailySalary.multiply(BigDecimal.valueOf(vacationDays));

        // Округляем до двух знаков после запятой
        return vacationPay.setScale(2, RoundingMode.HALF_UP);
    }

    private int calculateVacationDays(VacationData request) {
        if (request.getStartDate() != null && request.getEndDate() != null) {
            return calculateWithHolidays(request.getStartDate(), request.getEndDate());
        } else {
            return request.getVacationDays();
        }
    }

    private int calculateWithHolidays(LocalDate startDate, LocalDate endDate) {
        return (int) startDate.datesUntil(endDate.plusDays(1))
                .filter(this::isWorkingDay)
                .count();
    }

    private boolean isWorkingDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        boolean isWeekend = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
        boolean isHoliday = holidays.contains(date);
        return !isWeekend && !isHoliday;
    }
}
