package com.annachi.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.annachi.springboot.model.Employee;
import com.annachi.springboot.repository.EmployeeRepository;

@RestController
@RequestMapping("api/v1")
public class EmployeeController 
{
	@Autowired
    private EmployeeRepository employeeRepo;
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees()
	{		
		return ResponseEntity.ok(this.employeeRepo.findAll());
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@RequestParam("id") Long id)
	{
		Employee employee = employeeRepo.findById(id).orElse(null);
		return ResponseEntity.ok(employee);
	}
	
	
	@PostMapping("employees")
	public Employee createEmployee(@RequestBody Employee employee )
	{
		return employeeRepo.save(employee);
	}
	
	
	
	@PutMapping("employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long employeeId,@RequestBody Employee employee)
	{
		Employee oldEmployee= employeeRepo.findById(employeeId).orElse(null);
		oldEmployee.setEmail(employee.getEmail());
		oldEmployee.setLastName(employee.getFirstName());
		oldEmployee.setFirstName(employee.getLastName());
		employeeRepo.save(oldEmployee);
		return ResponseEntity.ok().body(oldEmployee);
	}
	
	
	
	
	@DeleteMapping("/employees")
	public Map<String,Boolean> deleteEmployee(@RequestParam("id") Long id)
	{
		Employee employee = employeeRepo.findById(id).orElse(null);
		employeeRepo.delete(employee);
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		map.put("deleted", Boolean.TRUE);
		return map;
	}
}
