package ru.marochkina.paycalculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationData {
    private BigDecimal averageSalary;
    private int vacationDays;
    private LocalDate startDate;
    private LocalDate endDate;
}
