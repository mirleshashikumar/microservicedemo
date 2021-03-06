package com.example.department.controller;

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

import com.example.department.model.CoreException;
import com.example.department.model.Department;
import com.example.department.service.DepartmentService;

@RestController
@RequestMapping("/departmentservice")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	@PostMapping("/create")
	public Department create(@RequestBody Department departmentRequest) {
		return departmentService.add(departmentRequest);
	}
	
	@GetMapping("/find/{id}")
	public Department findById(@PathVariable("id") Long id) {
		return departmentService.findById(id);
	}
	
	@GetMapping("/findall")
	public List<Department> findAll() {
		return departmentService.findAll();
  	}
	
	@DeleteMapping("/delete/{id}")
	public List<Department> deleteById() {
		return departmentService.findAll();
  	}
	
	@PutMapping("/update/{id}")
	public Department updateById(@RequestBody Department departmentRequest,@PathVariable("id") Long id) throws CoreException {
		return departmentService.updateById(departmentRequest,id);
  	}

	
}
