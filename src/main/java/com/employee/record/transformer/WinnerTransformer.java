package com.employee.record.transformer;

import com.employee.record.entity.Winner;
import com.employee.record.entity.WinnerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WinnerTransformer {
    private final EmployeeTransformer employeeTransformer;


    public WinnerResponse WinnerResponse(Winner entity) {
        return WinnerResponse.builder()
                .id(entity.getId())
                .employeeResponse(employeeTransformer.toEmployeeResponse(entity.getEmployee()))
                .year(entity.getYear())
                .month(entity.getMonth())
                .build();
    }}
