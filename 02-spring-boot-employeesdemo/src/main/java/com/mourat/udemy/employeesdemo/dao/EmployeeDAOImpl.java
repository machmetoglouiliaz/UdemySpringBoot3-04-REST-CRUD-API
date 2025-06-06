package com.mourat.udemy.employeesdemo.dao;

import com.mourat.udemy.employeesdemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    // declare entity manager
    private EntityManager entityManager;

    // autowire entity manager with constructor injection
    @Autowired
    public EmployeeDAOImpl (EntityManager entityManager){
        this.entityManager = entityManager;
    }

    // Implementations of interface methods

    @Override
    public List<Employee> findAll(){

        // create a typed query to get all employees
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);

        // execute the query and return the list of employees
        return query.getResultList();
    }

    @Override
    public Employee find(int id){

        // return employee with the given id
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee save(Employee employee){
        //id = 0 add
        //id != 0 update
        return entityManager.merge(employee);
    }

    @Override
    public void deleteById(int employeeId){
        entityManager.remove(entityManager.find(Employee.class, employeeId));
    }
}
