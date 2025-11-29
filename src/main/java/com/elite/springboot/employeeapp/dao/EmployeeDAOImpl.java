package com.elite.springboot.employeeapp.dao;

import com.elite.springboot.employeeapp.Entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> fromEmployee = entityManager.createQuery("FROM Employee", Employee.class);
        return fromEmployee.getResultList();
    }

    @Override
    public Employee findById(int empId) {
        return entityManager.find(Employee.class,empId);
    }

    @Override
    public Employee save(Employee emp) {
        return entityManager.merge(emp);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        entityManager.remove(employee);
    }
}
