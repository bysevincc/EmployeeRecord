package com.employee.record.service;

import com.employee.record.entity.WinnerResponse;
import com.employee.record.exception.WinnerNotFoundException;
import com.employee.record.transformer.WinnerTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WinnerService {
    private final WinnerPersistenceService winnerPersistenceService;
    private final EmployeeService employeeService;
    private final WinnerTransformer winnerTransformer;

    public  WinnerResponse getWinnerOfDate(int year, int month) {
        return winnerTransformer.WinnerResponse(winnerPersistenceService.getWinnerOfGivenDate(year,month)
                .orElseThrow(WinnerNotFoundException::new));
    }



}
