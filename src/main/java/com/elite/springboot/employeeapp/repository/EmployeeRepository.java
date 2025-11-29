package com.elite.springboot.employeeapp.repository;

import com.elite.springboot.employeeapp.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
