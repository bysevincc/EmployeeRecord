package com.employee.record.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequest {

    @NotNull(message = "first needs to be filled")
    private String firstName;

    @NotNull(message = "lastName needs to be filled")
    private String lastName;

    @NotNull
    private Long identity;

    @NotNull
    private Department department;

    @NotNull
    private String email;

    @NonNull
    private String location;

    @NotNull(message = "salary needs to be filleda")
    private Integer salary;

    @NotNull
            (message = "startDate for salary")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date startDate;
}
