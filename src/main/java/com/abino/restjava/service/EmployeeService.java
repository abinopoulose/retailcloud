package com.abino.restjava.service;

import com.abino.restjava.dto.CreateEmployeeDto;
import com.abino.restjava.dto.EmployeeResponseDto;
import com.abino.restjava.dto.UpdateEmployeeDto;
import com.abino.restjava.entity.Employee;
import com.abino.restjava.exceptions.AppException;
import com.abino.restjava.repository.DepartmentRepository;
import com.abino.restjava.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    @Autowired
    DepartmentRepository departmentRepository;

    public void addEmployee(CreateEmployeeDto createEmployeeDto) {
        try {

            Employee employee = new Employee();
            employee.setName(createEmployeeDto.getName());
            employee.setDob(createEmployeeDto.getDob());
            employee.setSalary(createEmployeeDto.getSalary());
            employee.setAddress(createEmployeeDto.getAddress());
            employee.setTitle(createEmployeeDto.getTitle());
            employee.setJoiningDate(createEmployeeDto.getJoiningDate());
            employee.setDepartment(departmentRepository.getReferenceById(createEmployeeDto.getDepartmentId()));

            if(createEmployeeDto.getManagerId() != null)
                employee.setManager(employeeRepository.getReferenceById(createEmployeeDto.getManagerId()));

            employeeRepository.save(employee);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new AppException("Failed to create Empoyee", HttpStatus.BAD_REQUEST);

        }
    }



    public void patchEmployee(Integer id, UpdateEmployeeDto updateEmployeeDto) {
        try {
            Employee employee = employeeRepository.getReferenceById(id);

            if (updateEmployeeDto.getName() != null) employee.setName(updateEmployeeDto.getName());
            if (updateEmployeeDto.getDob() != null) employee.setDob(updateEmployeeDto.getDob());
            if (updateEmployeeDto.getSalary() != null) employee.setSalary(updateEmployeeDto.getSalary());
            if (updateEmployeeDto.getAddress() != null) employee.setAddress(updateEmployeeDto.getAddress());
            if (updateEmployeeDto.getTitle() != null) employee.setTitle(updateEmployeeDto.getTitle());
            if (updateEmployeeDto.getJoiningDate() != null) employee.setJoiningDate(updateEmployeeDto.getJoiningDate());
            if (updateEmployeeDto.getDepartmentId() != null) {
                employee.setDepartment(departmentRepository.getReferenceById(updateEmployeeDto.getDepartmentId()));
            }
            if (updateEmployeeDto.getManagerId() != null) {
                employee.setManager(employeeRepository.getReferenceById(updateEmployeeDto.getManagerId()));
            }

            employeeRepository.save(employee);

        } catch (Exception e) {
            throw new AppException("Failed to update employee with empId " + id, HttpStatus.BAD_REQUEST);
        }
    }


    public void deleteEmployee(Integer id) {
        try {
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            throw new AppException("Failed to delete employee with id " + id, HttpStatus.BAD_REQUEST);
        }
    }

    public Page<EmployeeResponseDto> getEmployees(int page, int size, String name, Integer departmentId, String title) {
        Pageable pageable = PageRequest.of(page, size);
        String filterName = (name == null) ? null : "%" + name.toLowerCase() + "%";
        String filterTitle = (title == null) ? null : "%" + title.toLowerCase() + "%";


        Page<Employee> employeesPage = employeeRepository.findByFilters(filterName, departmentId, filterTitle, pageable);
        return employeesPage.map(this::toDto);

    }

    private EmployeeResponseDto toDto(Employee e) {
        EmployeeResponseDto dto = new EmployeeResponseDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setDob(e.getDob());
        dto.setSalary(e.getSalary());
        dto.setAddress(e.getAddress());
        dto.setTitle(e.getTitle());
        dto.setJoiningDate(e.getJoiningDate());
        dto.setDepartmentName(e.getDepartment().getName());
        dto.setManagerId(e.getManager() != null ? e.getManager().getId() : null);
        return dto;
    }


}
