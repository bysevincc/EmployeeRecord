package com.employee.record.service;

import com.employee.record.entity.Department;
import com.employee.record.entity.Employee;
import com.employee.record.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeePersistenceServiceTest {

    @InjectMocks
    @Autowired
    private EmployeePersistenceService employeePersistenceService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    void findAll() {
        // Given
        Employee employee1 = Employee.builder().id(1L).identity(12L).firstName("testName")
                .lastName("testLastName").salary(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").build();
        Employee employee2 = Employee.builder().id(2L).id(12L).firstName("testName")
                .lastName("testLastName").salary(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").build();

        List<Employee> list = Arrays.asList(employee1, employee2);
        //when
        when(employeeRepository.findAll()).thenReturn(list);

        //then
        List<Employee> foundList = employeePersistenceService.listOfEmployees();
        assertThat(foundList.size()).isEqualTo(2);

    }

    @Test
    void findByPublicId() {
        //Given
        Optional<Employee> employee = Optional.of(Employee.builder().id(1L).id(12L).firstName("testName")
                .lastName("").salary(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").build());

        //when
        when(employeeRepository.findByIdentity(any())).thenReturn(employee);

        //then
        Employee found = employeePersistenceService.findByIdentity(12L).get();
        assertThat(found.getId()).isEqualTo(employee.get().getId());
        assertThat(found.getFirstName()).isEqualTo(employee.get().getFirstName());
        assertThat(found.getLastName()).isEqualTo(employee.get().getLastName());
        assertThat(found.getSalary()).isEqualTo(employee.get().getSalary());
        assertThat(found.getDepartment()).isEqualTo(employee.get().getDepartment());
        assertThat(found.getStartedDate()).isEqualTo(employee.get().getStartedDate());
        assertThat(found.getLocation()).isEqualTo(employee.get().getLocation());
    }

    @Test
    void getSpecificEmployeeList() {
        //given
        Employee employee1 = Employee.builder().id(1L).identity(12L).firstName("testName")
                .lastName("testLastName").salary(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").build();
        Employee employee2 = Employee.builder().id(2L).identity(12l).firstName("testName")
                .lastName("testLastName").salary(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").build();

        List<Employee> list = Arrays.asList(employee1, employee2);
        Date date = new Date();

        //when
        when(employeeRepository.findByStartedDateGreaterThanAndSalaryGreaterThan(date, 2500)).thenReturn(list);

        //then
        List<Employee> foundList = employeePersistenceService.getSpecificEmployeeList(date, 2500);
        assertThat(foundList.size()).isEqualTo(2);

    }

    @Test
    void getShouldITDepartment() {
        //given
        Employee employee1 = Employee.builder().id(1L).identity(11l).firstName("testName")
                .lastName("testLastName").salary(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").build();
        Employee employee2 = Employee.builder().id(2L).identity(11l).firstName("testName")
                .lastName("testLastName").salary(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").build();

        List<Employee> list = Arrays.asList(employee1, employee2);

        // when
        when(employeeRepository.findByDepartment(any())).thenReturn(list);

        // then
        List<Employee> foundList = employeePersistenceService.findByDepartment(Department.IT);
        assertThat(foundList.size()).isEqualTo(2);
    }


}
