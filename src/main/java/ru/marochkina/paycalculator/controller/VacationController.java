package ru.marochkina.paycalculator.controller;

import ru.marochkina.paycalculator.model.VacationData;
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

    @PostMapping
    public ResponseEntity<VacationResponse> calculateVacationPay(@RequestBody VacationData request) {
        BigDecimal vacationPay = vacationService.calculateVacationPay(request);
        return ResponseEntity.ok(new VacationResponse(vacationPay));
    }
}
