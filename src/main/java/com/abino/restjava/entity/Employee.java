package com.abino.restjava.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private LocalDate dob;

    private Integer salary;

    @Column(length = 1000)
    private String address;

    private String title;

    private LocalDate joiningDate;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
