package com.abino.restjava.service;

import com.abino.restjava.dto.CreateEmployeeDto;
import com.abino.restjava.dto.UpdateEmployeeDto;
import com.abino.restjava.entity.Employee;
import com.abino.restjava.exceptions.AppException;
import com.abino.restjava.repository.DepartmentRepository;
import com.abino.restjava.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


}
