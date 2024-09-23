package ru.marochkina.paycalculator.test;

import ru.marochkina.paycalculator.service.VacationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.math.BigDecimal;

@SpringBootTest
public class VacationServiceTest {

    private final VacationService vacationService = new VacationService();

    @Test
    public void testCalculateVacationPayWithoutDates() {
        BigDecimal averageSalary = new BigDecimal("50000");
        int vacationDays = 14;

        BigDecimal vacationPay = vacationService.calculateVacationPay(averageSalary, vacationDays, null, null);

        BigDecimal expectedValue = new BigDecimal("23890.78");

        assertEquals(expectedValue, vacationPay);
    }

    @Test
    public void testCalculateVacationPayWithHolidays() {
        BigDecimal averageSalary = new BigDecimal("50000");
        String startDate = "2023-12-25";
        String endDate = "2024-01-10";

        BigDecimal expectedValue = new BigDecimal("13651.88");

        BigDecimal vacationPay = vacationService.calculateVacationPay(averageSalary, 0, startDate, endDate);
        assertEquals(expectedValue, vacationPay);
    }
}
