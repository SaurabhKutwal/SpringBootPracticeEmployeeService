package com.elite.springboot.employeeapp.service;

import com.elite.springboot.employeeapp.Entity.Employee;
import com.elite.springboot.employeeapp.exception.EmployeeNotFoundException;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> findALL();
    Employee findById(int empId);
    Employee save(Employee emp);
    Employee update(int empId, Map<String,Object> patch);
    Employee delete(int empId);
}
