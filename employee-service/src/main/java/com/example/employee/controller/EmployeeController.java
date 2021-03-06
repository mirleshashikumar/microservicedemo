package com.example.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.service.EmployeeService;

import services.employee.model.CoreException;
import services.employee.model.Employee;
import services.employee.model.EmployeeResponse;

@RestController
@RequestMapping("/employeeservice")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/create")
	public Employee create(@RequestBody Employee employeeRequest) {
		return employeeService.add(employeeRequest);
	}
	
	@GetMapping("/find/{id}")
	public EmployeeResponse findById(@PathVariable("id") Long id) {
		return employeeService.findById(id);
	}
	
	@GetMapping("/findall")
	public List<EmployeeResponse> findAll() {
		return employeeService.findAll();
  	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable("id") Long id) throws CoreException {
		employeeService.deleteById(id);
  	}
	
	@PutMapping("/update/{id}")
	public void updateById(@RequestBody Employee employeeRequest,@PathVariable("id") Long id) throws CoreException {
		employeeService.updateById(employeeRequest, id);;
  	}
	
	
}
