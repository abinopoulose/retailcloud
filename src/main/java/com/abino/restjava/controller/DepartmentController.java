package com.abino.restjava.controller;


import com.abino.restjava.dto.CreateDepartmentDto;
import com.abino.restjava.dto.UpdateDepartmentDto;
import com.abino.restjava.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<String> createDepartment(@RequestBody CreateDepartmentDto dto) {
        departmentService.addDepartment(dto);
        return new ResponseEntity<>("Department created successfully", HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchDepartment(
            @PathVariable Integer id,
            @RequestBody UpdateDepartmentDto dto) {
        departmentService.patchDepartment(id, dto);
        return ResponseEntity.ok("Department updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Integer id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("Department deleted successfully");
    }


}
