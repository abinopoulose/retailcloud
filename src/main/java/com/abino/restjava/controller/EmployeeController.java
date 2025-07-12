package com.abino.restjava.controller;


import com.abino.restjava.dto.CreateEmployeeDto;
import com.abino.restjava.dto.UpdateEmployeeDto;
import com.abino.restjava.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<String> createEmployee (@RequestBody CreateEmployeeDto createEmployeeDto){
        employeeService.addEmployee(createEmployeeDto);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchEmployee(
            @PathVariable Integer id,
            @RequestBody UpdateEmployeeDto updateEmployeeDto) {
        employeeService.patchEmployee(id, updateEmployeeDto);
        return ResponseEntity.ok("Employee updated successfully");
    }


}
