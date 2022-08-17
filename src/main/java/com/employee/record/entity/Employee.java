package com.employee.record.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "employee")
@Table(name = "employee")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "identity",unique = true, nullable = false)
    private Long identity;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TITLE")
    private String title;

    @Column(nullable = false)
    private Integer salary;

    @Column(nullable = false)
    private Department department;

    @Column(nullable = false)
    private Date startedDate;

    @Column(nullable = false)
    private String location;



}