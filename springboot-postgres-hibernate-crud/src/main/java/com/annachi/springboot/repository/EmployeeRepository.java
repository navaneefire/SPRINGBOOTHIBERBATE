package com.annachi.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.annachi.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
	
}
