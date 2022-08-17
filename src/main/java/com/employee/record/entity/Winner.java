package com.employee.record.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "winner")
@Table(name = "winner")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Winner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee")
    private Employee employee;

    @Column
    private int year;

    @Column
    private int month;
}
