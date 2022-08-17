package com.employee.record.service;

import com.employee.record.entity.Department;
import com.employee.record.entity.Employee;
import com.employee.record.entity.EmployeeResponse;
import com.employee.record.transformer.EmployeeTransformer;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeeServiceTest {

    @MockBean
    private EmployeePersistenceService employeePersistenceService;
    @MockBean
    private EmployeeTransformer employeeTransformer;

    @Autowired
    @InjectMocks
    private EmployeeService employeeService;


    @Test
    void test_should_find_all() {
        //Given
        Employee employee1 = Employee.builder().id(1L).identity(44l).firstName("testName")
                .lastName("testLastName").salary(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").build();
        Employee employee2 = Employee.builder().id(2L).identity(44l).firstName("testName")
                .lastName("testLastName").salary(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").build();
        EmployeeResponse employeeResponse1 = EmployeeResponse.builder()
                .identity(employee1.getIdentity()).firstName(employee1.getFirstName()).lastName(employee1.getLastName())
                .salary(employee1.getSalary()).department(employee1.getDepartment()).startedDate(employee1.getStartedDate())
                .location(employee1.getLocation()).build();
        EmployeeResponse employeeResponse2 = EmployeeResponse.builder()
                .identity(employee2.getIdentity()).firstName(employee2.getFirstName()).lastName(employee2.getLastName())
                .salary(employee2.getSalary()).department(employee2.getDepartment()).startedDate(employee2.getStartedDate())
                .location(employee2.getLocation()).build();

        //when
        when(employeeTransformer.listOfEmployeeList(any())).thenReturn(Arrays.asList(employeeResponse1, employeeResponse2));

        //then
        assertThat(employeeService.listOfEmployees()).isEqualTo(Arrays.asList(employeeResponse1, employeeResponse2));
    }

    @Test
    void findById() {
        //Given
        Employee employee = Employee.builder().id(1L).identity(45l).firstName("testName")
                .lastName("testLastName").salary(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").build();

        EmployeeResponse employeeResponse = EmployeeResponse.builder()
                .identity(employee.getIdentity()).firstName(employee.getFirstName()).lastName(employee.getLastName())
                .salary(employee.getSalary()).department(employee.getDepartment()).startedDate(employee.getStartedDate())
                .location(employee.getLocation()).build();

        // when
        when(employeePersistenceService.findByIdentity(any())).thenReturn(Optional.of(employee));
        when(employeeTransformer.toEmployeeResponse(employee)).thenReturn(employeeResponse);

        //then
        EmployeeResponse found = employeeService.findById(employee.getIdentity());

        assertDoesNotThrow(() -> employeeService.findById(employee.getIdentity()));
        assertThat(found.getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(found.getLastName()).isEqualTo(employee.getLastName());
        assertThat(found.getSalary()).isEqualTo(employee.getSalary());
        assertThat(found.getDepartment()).isEqualTo(employee.getDepartment());
        assertThat(found.getStartedDate()).isEqualTo(employee.getStartedDate());
        assertThat(found.getLocation()).isEqualTo(employee.getLocation());
    }



}