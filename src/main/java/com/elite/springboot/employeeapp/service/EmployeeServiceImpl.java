package com.elite.springboot.employeeapp.service;

import com.elite.springboot.employeeapp.Entity.Employee;
import com.elite.springboot.employeeapp.dao.EmployeeDAO;
import com.elite.springboot.employeeapp.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;
    private JsonMapper jsonMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO, JsonMapper jsonMapper){
        this.employeeDAO = employeeDAO;
        this.jsonMapper = jsonMapper;
    }

    @Override
    public List<Employee> findALL() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int empId){

        try{
            Employee dbEmp = employeeDAO.findById(empId);
            if(dbEmp == null){
                throw new EmployeeNotFoundException("Employee not found for Id " + empId);
            }
            return dbEmp;
        } catch (EmployeeNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Employee save(Employee emp) {
        return employeeDAO.save(emp);
    }

    @Override
    @Transactional
    public Employee update(int empId, Map<String, Object> patch) {
        Employee dbEmp = employeeDAO.findById(empId);

        if(dbEmp == null){
            throw new RuntimeException("Employee Not found for id " + empId);
        }

        if(patch.containsKey("id")){
            throw new RuntimeException("Id can't be updated");
        }

        Employee updated = applyPatch(dbEmp, patch);

        return employeeDAO.save(updated);
    }

    private Employee applyPatch(Employee dbEmp, Map<String, Object> patch) {
        ObjectNode old = jsonMapper.convertValue(dbEmp, ObjectNode.class);
        ObjectNode newPatch = jsonMapper.convertValue(patch, ObjectNode.class);

        old.setAll(newPatch);

        return jsonMapper.convertValue(old, Employee.class);
    }

    @Override
    @Transactional
    public Employee delete(int empId) {
        Employee emp = employeeDAO.findById(empId);
        if(emp == null){
            throw new RuntimeException("Employee not found for id " + empId);
        }
        employeeDAO.deleteEmployee(emp);
        return emp;
    }
}
