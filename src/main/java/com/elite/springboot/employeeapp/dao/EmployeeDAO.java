package com.elite.springboot.employeeapp.dao;

import com.elite.springboot.employeeapp.Entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int empId);
    Employee save(Employee emp);
    void deleteEmployee(Employee employee);
}
