package com.employee.record.controller;


import com.employee.record.entity.*;
import com.employee.record.service.EmployeeService;
import com.employee.record.service.WinnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

import static com.employee.record.util.DateUtils.getTodayMonthValue;
import static com.employee.record.util.DateUtils.getTodayYearValue;


@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final WinnerService winnerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@Valid @RequestBody CreateEmployeeRequest createEmployeeRequest) {
        employeeService.createEmployee(createEmployeeRequest);
    }

    @GetMapping("/{identity}")
    public EmployeeResponse findEmployeeById(@PathVariable Long identity) {
        return employeeService.findById(identity);
    }

    @GetMapping
    public List<EmployeeResponse> listOfEmployees() {
        return employeeService.listOfEmployees();
    }

    @PutMapping("/{identity}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployee(@PathVariable Long identity,
                               @RequestBody UpdateEmployeeRequest updateEmployee) {
        employeeService.updateEmployee(identity, updateEmployee);
    }

    @DeleteMapping("/{identity}")
    public void deleteEmployee(@PathVariable Long identity) {
        employeeService.deleteEmployee(identity);
    }

    @PutMapping("/department/{department}")
    public void updateLocationsForEmployee(@RequestBody ClientUpdateRequest request, @PathVariable Department department) {
        employeeService.updateLocationsForEmployee(request, department);

    }

    @GetMapping("/specific")
    public List<EmployeeResponse> specificEmployees(@RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
                                                     @RequestParam("salary") String salary) {
        return employeeService.getSpecificEmployeeList(startDate, Integer.parseInt(salary));
    }

    @GetMapping("/winner")
    public WinnerResponse getThisMonthWinnerEmployee() {
        return winnerService.getWinnerOfDate(getTodayYearValue(), getTodayMonthValue());
    }

    @GetMapping("/winner/winner-date")
    public WinnerResponse getWinner(@RequestParam(value = "year") String year, @RequestParam("month") String month) {
        return winnerService.getWinnerOfDate(Integer.parseInt(year), Integer.parseInt(month));
    }
}


