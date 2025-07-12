package com.abino.restjava.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    @Column(name = "created_at")
    private LocalDate creationDate;

    @OneToOne
    @JoinColumn(name = "department_head_id", referencedColumnName = "id")
    private Employee departmentHead;


}
