package com.employee.record.transformer;


import com.employee.record.entity.CreateEmployeeRequest;
import com.employee.record.entity.Employee;
import com.employee.record.entity.EmployeeResponse;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeTransformer {

    public Employee requestToEntity(CreateEmployeeRequest employeeRequest) {
        Date now = new Date();
        return Employee.builder()
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .salary(employeeRequest.getSalary())
                .startedDate(employeeRequest.getStartDate())
                .email(employeeRequest.getEmail())
                .department(employeeRequest.getDepartment())
                .location(employeeRequest.getLocation())
                .identity(employeeRequest.getIdentity())
                .build();
    }
    public EmployeeResponse toEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .identity(employee.getIdentity())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .startedDate(employee.getStartedDate())
                .email(employee.getEmail())
                .department(employee.getDepartment())
                .location(employee.getLocation())
                .build();
    }

    public List<EmployeeResponse> listOfEmployeeList(List<Employee> employeeList) {
        return employeeList
                .stream()
                .map(this::toEmployeeResponse)
                .collect(Collectors.toList());
    }


}
