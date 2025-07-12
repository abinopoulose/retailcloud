package com.abino.restjava.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateDepartmentDto {

    @NotBlank(message = "Department name is required")
    @Size(min = 3, max = 255, message = "Department name must be between 3 and 255 characters")
    private String name;

    @NotNull(message = "Creation date is required")
    private LocalDate creationDate;

    @NotNull(message = "Department head ID is required")
    private Integer departmentHeadId;
}
