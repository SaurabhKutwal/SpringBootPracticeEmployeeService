package com.elite.springboot.employeeapp.controller;

import com.elite.springboot.employeeapp.Entity.Employee;
import com.elite.springboot.employeeapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {



    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    //Get All Employees
    @GetMapping
    public List<Employee> findAll(){
        return employeeService.findALL();
    }

    //Get Employee by id
    @GetMapping("/{empId}")
    public Employee findById(@PathVariable int empId){
        return employeeService.findById(empId);
    }

    //Add Employee

    //Update Employee Detail

    //Delete an Employee
}
