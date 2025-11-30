package com.elite.springboot.employeeapp.service;

import com.elite.springboot.employeeapp.Entity.Employee;
import com.elite.springboot.employeeapp.dao.EmployeeDAO;
import com.elite.springboot.employeeapp.exception.EmployeeNotFoundException;
import com.elite.springboot.employeeapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    //private EmployeeDAO employeeDAO;
    private JsonMapper jsonMapper;
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, JsonMapper jsonMapper){
        this.employeeRepository = employeeRepository;
        this.jsonMapper = jsonMapper;
    }

    @Override
    public List<Employee> findALL() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int empId){

        try{
            Optional<Employee> opt = employeeRepository.findById(empId);
            if(opt.isEmpty()){
                throw new EmployeeNotFoundException("Employee not found for Id " + empId);
            }
            return opt.get();
        } catch (EmployeeNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee save(Employee emp) {
        return employeeRepository.save(emp);
    }

    @Override
    public Employee update(int empId, Map<String, Object> patch) {
        Optional<Employee> opt = employeeRepository.findById(empId);

        if(opt.isEmpty()){
            throw new RuntimeException("Employee Not found for id " + empId);
        }

        if(patch.containsKey("id")){
            throw new RuntimeException("Id can't be updated");
        }

        Employee updated = applyPatch(opt.get(), patch);

        return employeeRepository.save(updated);
    }

    private Employee applyPatch(Employee dbEmp, Map<String, Object> patch) {
        ObjectNode old = jsonMapper.convertValue(dbEmp, ObjectNode.class);
        ObjectNode newPatch = jsonMapper.convertValue(patch, ObjectNode.class);

        old.setAll(newPatch);

        return jsonMapper.convertValue(old, Employee.class);
    }

    @Override
    public Employee delete(int empId) {
        Optional<Employee> opt = employeeRepository.findById(empId);
        if(opt.isEmpty()){
            throw new RuntimeException("Employee not found for id " + empId);
        }
        employeeRepository.delete(opt.get());
        return opt.get();
    }
}
