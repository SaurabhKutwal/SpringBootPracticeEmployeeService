package com.elite.springboot.employeeapp.controller;

import com.elite.springboot.employeeapp.Entity.Employee;
import com.elite.springboot.employeeapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public Employee findById(@PathVariable int empId) {
        return employeeService.findById(empId);
    }

    //Add Employee

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    //Update Employee Detail

    @PatchMapping("/{empId}")
    public Employee updateEmployee(@PathVariable int empId, @RequestBody Map<String, Object> patch){
        return employeeService.update(empId, patch);
    }

    //Delete an Employee

    @DeleteMapping("/{empId}")
    public Employee deleteEmployee(@PathVariable int empId){
        return employeeService.delete(empId);
    }
}
