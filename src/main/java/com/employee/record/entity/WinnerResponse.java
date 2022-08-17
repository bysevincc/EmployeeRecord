package com.employee.record.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WinnerResponse {
    private Long id;
    private EmployeeResponse employeeResponse;
    private int year;
    private int month;
}
