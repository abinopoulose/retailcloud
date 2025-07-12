package com.abino.restjava.repository;


import com.abino.restjava.dto.CreateEmployeeDto;
import com.abino.restjava.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
