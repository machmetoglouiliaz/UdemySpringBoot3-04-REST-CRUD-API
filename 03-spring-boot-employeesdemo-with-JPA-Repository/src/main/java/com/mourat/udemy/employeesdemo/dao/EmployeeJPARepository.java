package com.mourat.udemy.employeesdemo.dao;

import com.mourat.udemy.employeesdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeJPARepository extends JpaRepository<Employee, Integer> {
}
