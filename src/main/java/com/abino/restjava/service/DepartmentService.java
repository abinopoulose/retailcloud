package com.abino.restjava.service;

import com.abino.restjava.dto.CreateDepartmentDto;
import com.abino.restjava.dto.UpdateDepartmentDto;
import com.abino.restjava.entity.Department;
import com.abino.restjava.entity.Employee;
import com.abino.restjava.exceptions.AppException;
import com.abino.restjava.repository.DepartmentRepository;
import com.abino.restjava.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public void addDepartment(CreateDepartmentDto dto) {
        try {
            Department department = new Department();
            department.setName(dto.getName());
            department.setCreationDate(dto.getCreationDate());

            department.setDepartmentHead(employeeRepository.getReferenceById(dto.getDepartmentHeadId()));

            departmentRepository.save(department);
        } catch (Exception e) {
            throw new AppException("Failed to create department", HttpStatus.BAD_REQUEST);
        }
    }

    public void patchDepartment(Integer id, UpdateDepartmentDto dto) {
        try {
            Department department = departmentRepository.getReferenceById(id);

            if (dto.getName() != null) {
                department.setName(dto.getName());
            }

            if (dto.getCreationDate() != null) {
                department.setCreationDate(dto.getCreationDate());
            }

            if (dto.getDepartmentHeadId() != null) {
                department.setDepartmentHead(employeeRepository.getReferenceById(dto.getDepartmentHeadId()));
            }

            departmentRepository.save(department);
        } catch (Exception e) {
            throw new AppException("Failed to update department with id " + id, HttpStatus.BAD_REQUEST);
        }
    }

    public void deleteDepartment(Integer id) {
        try {
            departmentRepository.deleteById(id);
        } catch (Exception e) {
            throw new AppException("Failed to delete department with id " + id, HttpStatus.BAD_REQUEST);
        }
    }

}