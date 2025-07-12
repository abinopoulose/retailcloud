package com.abino.restjava.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateDepartmentDto {

    @Size(min = 2, max = 255, message = "Department name must be between 2 and 255 characters")
    private String name;

    @PastOrPresent
    private LocalDate creationDate;

    private Integer departmentHeadId;
}
