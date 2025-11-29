package com.elite.springboot.employeeapp.dao;

import com.elite.springboot.employeeapp.Entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int empId);
    Employee addEmployee(Employee emp);
    Employee updateEmployee(int empId, Map<String, Object> patch);
    Employee deleteEmployee(int empId);
}
