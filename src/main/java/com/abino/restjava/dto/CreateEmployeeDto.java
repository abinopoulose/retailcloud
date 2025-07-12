package com.abino.restjava.dto;

import com.abino.restjava.entity.Department;
import com.abino.restjava.entity.Employee;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateEmployeeDto {

    @NotNull(message = "Name is required")
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    private String name;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    @NotNull(message = "Salary is required")
    @Min(value = 0, message = "Salary must be positive")
    private Integer salary;

    @NotNull(message = "Address is required")
    @Size(min = 10, max = 1000, message = "Address must be between 10 and 1000 characters")
    private String address;

    @NotNull(message = "Title is required")
    @Size(min = 2, max = 255, message = "Title must be between 2 and 255 characters")
    private String title;

    @NotNull(message = "Joining date is required")
    @PastOrPresent(message = "Joining date cannot be in the future")
    private LocalDate joiningDate;

    private Integer managerId;

    @NotNull(message = "Department is required")
    private Integer departmentId;
}
