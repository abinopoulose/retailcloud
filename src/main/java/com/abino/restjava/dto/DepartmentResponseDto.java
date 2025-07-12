package com.abino.restjava.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DepartmentResponseDto {

    private Integer id;
    private String name;
    private LocalDate createdAt;

    private SimpleEmployeeDto departmentHead;
    private List<SimpleEmployeeDto> employees;

}
