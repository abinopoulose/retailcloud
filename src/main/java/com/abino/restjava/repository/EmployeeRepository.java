package com.abino.restjava.repository;


import com.abino.restjava.dto.CreateEmployeeDto;
import com.abino.restjava.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("""
        SELECT e FROM Employee e
        WHERE (:name IS NULL OR LOWER(e.name) LIKE :name)
          AND (:departmentId IS NULL OR e.department.id = :departmentId)
          AND (:title IS NULL OR LOWER(e.title) LIKE :title)
    """)
    Page<Employee> findByFilters(
            @Param("name") String name,
            @Param("departmentId") Integer departmentId,
            @Param("title") String title,
            Pageable pageable
    );

}
