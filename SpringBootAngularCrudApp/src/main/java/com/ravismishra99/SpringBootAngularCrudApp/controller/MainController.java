package com.ravismishra99.SpringBootAngularCrudApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravismishra99.SpringBootAngularCrudApp.entity.Employee;
import com.ravismishra99.SpringBootAngularCrudApp.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {
	
	@Autowired
	private EmployeeRepository repository;

	@GetMapping("/employee")
	public List<Employee> getAllEmployees()
	{
		return repository.findAll();
	}
	
	@PostMapping("/employee")
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return repository.save(employee);
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmployeebyID(@PathVariable int id)
	{
		return repository.findById(id).orElse(new Employee());
	}
	
	@DeleteMapping("/employee/{id}")
	public Employee deleteEmployeebyID(@PathVariable int id)
	{
		Employee emp = repository.findById(id).orElse(null);
		if(emp!=null)
		{
			repository.deleteById(id);
		}
		return emp;
	}
	
	
	
	
}
