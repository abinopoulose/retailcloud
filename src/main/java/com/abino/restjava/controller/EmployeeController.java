package com.abino.restjava.controller;


import com.abino.restjava.dto.CreateEmployeeDto;
import com.abino.restjava.dto.EmployeeResponseDto;
import com.abino.restjava.dto.UpdateEmployeeDto;
import com.abino.restjava.entity.Employee;
import com.abino.restjava.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer departmentId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false, defaultValue = "false") boolean lookup
    ) {
        if (lookup) {
            Page<EmployeeResponseDto> employees = employeeService.getEmployees(page, size, name, departmentId, title);
            return ResponseEntity.ok(employees.getContent());
        } else {
            return ResponseEntity.ok(java.util.Collections.emptyList());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Integer id) {
        EmployeeResponseDto employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }


}
