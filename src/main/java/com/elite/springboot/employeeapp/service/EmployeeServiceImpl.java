package com.elite.springboot.employeeapp.service;

import com.elite.springboot.employeeapp.Entity.Employee;
import com.elite.springboot.employeeapp.dao.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findALL() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int empId) {
        return employeeDAO.findById(empId);
    }

    @Override
    public Employee save(Employee emp) {
        return null;
    }

    @Override
    public Employee update(int empId, Map<String, Object> patch) {
        return null;
    }

    @Override
    public Employee delete(int empId) {
        return null;
    }
}
