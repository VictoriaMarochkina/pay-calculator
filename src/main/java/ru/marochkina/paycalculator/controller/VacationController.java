package ru.marochkina.paycalculator.controller;

import ru.marochkina.paycalculator.model.VacationResponse;
import ru.marochkina.paycalculator.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/calculate")
public class VacationController {

    @Autowired
    private VacationService vacationService;

    @GetMapping
    public ResponseEntity<VacationResponse> calculateVacationPay(@RequestParam BigDecimal averageSalary,
                                                                 @RequestParam int vacationDays,
                                                                 @RequestParam(required = false) String startDate,
                                                                 @RequestParam(required = false) String endDate) {
        BigDecimal vacationPay = vacationService.calculateVacationPay(averageSalary, vacationDays, startDate, endDate);
        return ResponseEntity.ok(new VacationResponse(vacationPay));
    }
}
