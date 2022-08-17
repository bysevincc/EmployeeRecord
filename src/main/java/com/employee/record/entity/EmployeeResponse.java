package com.employee.record.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class EmployeeResponse {

    private Long identity;
    private String firstName;
    private String lastName;
    private Department department;
    private Integer salary;
    private String email;
    private Date startedDate;
    private String location;


}
