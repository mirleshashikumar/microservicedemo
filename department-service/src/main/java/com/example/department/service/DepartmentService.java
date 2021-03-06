package com.example.department.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.department.model.CoreException;
import com.example.department.model.Department;
import com.example.department.repository.IDepartmentRepository;


@Service("departmentService")
public class DepartmentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);

	@Autowired
	IDepartmentRepository repository;
		
	
	public Department add(@RequestBody Department department) {
		LOGGER.info("Department add: {}", department);
		return repository.save(department);
	}
	
	public Department findById(@PathVariable("id") Long id) {
		Department department=new Department();
		LOGGER.info("Department find: id={}", id);
		 Optional<Department> departmentResponse = repository.findById(id);
		 if(departmentResponse.isPresent()) {
			 department=departmentResponse.get();
		 }
		 return department;
	}
	
	public List<Department> findAll() {
		LOGGER.info("all Department");
		return repository.findAll();
	}
	
	public void deleteById(Long id) throws CoreException {
		 Optional<Department> employeeResponse = repository.findById(id);
		 if(employeeResponse.isPresent()) {
			 repository.deleteById(id);
		 }else {
			 String message="Resource not found";
		 throw new CoreException(message) ;
		 }
		
	}	
	
	public Department updateById(Department department,Long id) throws CoreException {
		 Optional<Department> employeeResponse = repository.findById(id);
		 if(employeeResponse.isPresent()) {
			 repository.save(department);
		 }else {
			 String message="Resource not found";
		 throw new CoreException(message) ;
		 }
		return employeeResponse.get();
		
	}	
}
