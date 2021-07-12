package com.example.organization.controller;

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

import com.example.organization.model.CoreException;
import com.example.organization.model.Organization;
import com.example.organization.service.OrganizationService;

@RestController
@RequestMapping("/organizationservice")
public class OrganizationController {

	@Autowired
	OrganizationService departmentService;
	
	@PostMapping("/create")
	public Organization create(@RequestBody Organization organization) {
		return departmentService.add(organization);
	}
	
	@GetMapping("/find/{id}")
	public Organization findById(@PathVariable("id") Long id) {
		return departmentService.findById(id);
	}
	
	@GetMapping("/findall")
	public List<Organization> findAll() {
		return departmentService.findAll();
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable("id") Long id) throws CoreException {
		departmentService.deleteById(id);
	}
	
	@PutMapping("/update/{id}")
	public Organization updateById(@RequestBody Organization organization,@PathVariable("id") Long id) throws CoreException {
		return departmentService.updateById(organization,id);
	}
	
	
}
