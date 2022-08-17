package com.employee.record.service;

import com.employee.record.entity.*;
import com.employee.record.exception.WinnerNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class WinnerServiceTest {

    @Autowired
    @InjectMocks
    private WinnerService winnerService;

    @MockBean
    private WinnerPersistenceService winnerPersistenceService;

    @Test
    void testGetWinner() {
        //given
        Employee employee1 = Employee.builder().id(1L).identity(22l).firstName("testName")
                .lastName("testLastName").salary(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").build();
        EmployeeResponse employeeResponse = EmployeeResponse.builder()
                .identity(23l).firstName("testName").lastName("testLastName")
                .salary(2500).department(Department.IT).startedDate(new Date())
                .location("turkey").build();
        WinnerResponse winnerResponse = WinnerResponse.builder().id(1L)
                .employeeResponse(employeeResponse).year(2021).month(8).build();
        Winner winner = new Winner(1L, employee1, 2021, 8);


        //when
        when(winnerPersistenceService.getWinnerOfGivenDate(anyInt(), anyInt())).thenReturn(Optional.of(winner));

        //then
        WinnerResponse found = winnerService.getWinnerOfDate(2021, 8);
        assertThat(found.getMonth()).isEqualTo(winnerResponse.getMonth());
        assertThat(found.getYear()).isEqualTo(winnerResponse.getYear());
        assertThat(found.getEmployeeResponse().getFirstName()).isEqualTo(winnerResponse.getEmployeeResponse().getFirstName());
    }

    @Test
    void test_should_throw_Winner_Not_Found_exception() {
        assertThrows(WinnerNotFoundException.class, () -> winnerService.getWinnerOfDate(2021, 8));
    }
}