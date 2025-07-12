package com.abino.restjava.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EmployeeResponseDto {
    private Integer id;

    private String name;

    private LocalDate dob;

    private Integer salary;

    private String address;

    private String title;

    private LocalDate joiningDate;

    private DepartmentDto department;

    private ManagerDto manager;
}
