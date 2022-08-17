package com.employee.record.service;

import com.employee.record.entity.Department;
import com.employee.record.entity.Employee;
import com.employee.record.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeePersistenceService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> listOfEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public void save(Employee employee) {
         employeeRepository.save(employee);
    }

    public Optional<Employee> findByIdentity(Long identity) {
        return employeeRepository.findByIdentity(identity);
    }

    public void deleteEmployee(Long identity) {
         employeeRepository.deleteById(identity);
    }

    public List<Employee> findByDepartment(Department department) {
        return employeeRepository.findByDepartment(department);
    }

    public List<Employee> getSpecificEmployeeList(Date date, int salary) {
    return  employeeRepository.findByStartedDateGreaterThanAndSalaryGreaterThan(date,salary);   }
}
