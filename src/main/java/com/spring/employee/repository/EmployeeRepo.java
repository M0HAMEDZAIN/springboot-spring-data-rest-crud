package com.spring.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.employee.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
