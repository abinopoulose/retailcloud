package com.abino.restjava.service;

import com.abino.restjava.dto.CreateDepartmentDto;
import com.abino.restjava.dto.DepartmentResponseDto;
import com.abino.restjava.dto.SimpleEmployeeDto;
import com.abino.restjava.dto.UpdateDepartmentDto;
import com.abino.restjava.entity.Department;
import com.abino.restjava.entity.Employee;
import com.abino.restjava.exceptions.AppException;
import com.abino.restjava.repository.DepartmentRepository;
import com.abino.restjava.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public DepartmentResponseDto getDepartmentById(Integer id, String expand) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (departmentOptional.isPresent()) {
            Department department = departmentOptional.get();
            DepartmentResponseDto dto = toDto(department);

            if ("employees".equalsIgnoreCase(expand)) {
                List<Employee> employees = employeeRepository.findByDepartmentId(department.getId());
                dto.setEmployees(toSimpleEmployeeDtoList(employees));
            }

            return dto;

        } else {
            throw new AppException("Department with id " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    private List<SimpleEmployeeDto> toSimpleEmployeeDtoList(List<Employee> employees) {
        return employees.stream()
                .map(this::toSimpleEmployeeDto)
                .collect(Collectors.toList());
    }
    private SimpleEmployeeDto toSimpleEmployeeDto(Employee employee) {
        SimpleEmployeeDto dto = new SimpleEmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        return dto;
    }


    private DepartmentResponseDto toDto(Department department) {
        DepartmentResponseDto dto = new DepartmentResponseDto();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setCreatedAt(department.getCreationDate());

        if (department.getDepartmentHead() != null) {
            SimpleEmployeeDto headDto = new SimpleEmployeeDto();
            headDto.setId(department.getDepartmentHead().getId());
            headDto.setName(department.getDepartmentHead().getName());
            dto.setDepartmentHead(headDto);
        }
        return dto;
    }

}