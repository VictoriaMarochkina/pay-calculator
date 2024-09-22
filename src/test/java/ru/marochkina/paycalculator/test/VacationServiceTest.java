package ru.marochkina.paycalculator.test;

import ru.marochkina.paycalculator.service.VacationService;
import ru.marochkina.paycalculator.model.VacationData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.math.BigDecimal;

@SpringBootTest
public class VacationServiceTest {

    private final VacationService vacationService = new VacationService();

    @Test
    public void testCalculateVacationPay() {
        VacationData request = new VacationData(new BigDecimal("50000"), 14, null, null);
        BigDecimal vacationPay = vacationService.calculateVacationPay(request);

        BigDecimal expectedValue = new BigDecimal("23890.78");

        assertEquals(expectedValue, vacationPay);
    }

    @Test
    public void testCalculateVacationPayWithHolidays() {
        VacationData request = new VacationData(new BigDecimal("50000"), 0, LocalDate.of(2023, 12, 25), LocalDate.of(2024, 1, 10));

        BigDecimal expectedValue = new BigDecimal("13651.88");

        BigDecimal vacationPay = vacationService.calculateVacationPay(request);
        assertEquals(expectedValue, vacationPay);
    }
}
