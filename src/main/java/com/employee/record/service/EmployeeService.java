package com.employee.record.service;

import com.employee.record.entity.*;
import com.employee.record.exception.EmployeeNotFoundException;
import com.employee.record.transformer.EmployeeTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeTransformer employeeTransformer;
    private final EmployeePersistenceService employeePersistenceService;


    public void createEmployee(CreateEmployeeRequest createEmployeeRequest) {
        employeePersistenceService.save(employeeTransformer.requestToEntity(createEmployeeRequest));
    }

    public EmployeeResponse findById(Long identity) {
        return employeeTransformer.toEmployeeResponse(employeePersistenceService.findByIdentity(identity).orElseThrow(EmployeeNotFoundException::new));
    }

    public List<EmployeeResponse> listOfEmployees() {
        return employeeTransformer.listOfEmployeeList(employeePersistenceService.listOfEmployees());
    }


    public void updateEmployee(Long identity, UpdateEmployeeRequest updateEmployee) {
       Employee employee=employeePersistenceService.findByIdentity(identity).orElseThrow(EmployeeNotFoundException::new);
        if (Objects.nonNull(updateEmployee.getFirstName())) {
            employee.setFirstName(updateEmployee.getFirstName());
        }
        if (Objects.nonNull(updateEmployee.getLastName())) {
            employee.setLastName(updateEmployee.getLastName());
        }
        if (Objects.nonNull(updateEmployee.getSalary())) {
            employee.setSalary(updateEmployee.getSalary());
        }
        if (Objects.nonNull(updateEmployee.getLocation())) {
            employee.setLocation(updateEmployee.getLocation());
        }
        if (Objects.nonNull(updateEmployee.getEmail())) {
            employee.setEmail(updateEmployee.getEmail());
        }
        if (Objects.nonNull(updateEmployee.getDepartment())) {
            employee.setDepartment(updateEmployee.getDepartment());
        }
        if (Objects.nonNull(updateEmployee.getStartDate())) {
            employee.setStartedDate(updateEmployee.getStartDate());
        }

employeePersistenceService.save(employee);

    }
    public void deleteEmployee(Long identity) {
employeePersistenceService.deleteEmployee(identity);
    }


    public void updateLocationsForEmployee(ClientUpdateRequest request, Department department) {
        List<Employee> employees = employeePersistenceService.findByDepartment(department);
        employees.forEach(employee -> {
            employee.setLocation(request.getLocation());
            employeePersistenceService.save(employee);
        });

    }

    public List<EmployeeResponse> getSpecificEmployeeList(Date startDate, int salary) {
        return employeeTransformer.listOfEmployeeList(
                employeePersistenceService.getSpecificEmployeeList(startDate, salary)
        );
    }
}
