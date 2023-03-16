package com.hr.microservices.EmployeeMs.EmployeeMs.repository;

import com.hr.microservices.EmployeeMs.EmployeeMs.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    List<Employee> findByPositionIgnoreCase(String position);

    List<Employee> findByNameStartsWithIgnoreCase(String name);

    List<Employee> findByStartDateBetween(LocalDateTime start, LocalDateTime end);
}
