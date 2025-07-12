package com.abino.restjava.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateEmployeeDto {

    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    private String name;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    @Min(value = 0, message = "Salary must be positive")
    private Integer salary;

    @Size(min = 10, max = 1000, message = "Address must be between 10 and 1000 characters")
    private String address;

    @Size(min = 2, max = 255, message = "Title must be between 2 and 255 characters")
    private String title;

    @PastOrPresent(message = "Joining date cannot be in the future")
    private LocalDate joiningDate;

    private Integer managerId;

    private Integer departmentId;

}
