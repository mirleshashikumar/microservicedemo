package com.example.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.employee.repository.EmployeeRepository;

import services.employee.model.CoreException;
import services.employee.model.DepartmentResponse;
import services.employee.model.Employee;
import services.employee.model.EmployeeResponse;
import services.employee.model.OrganizationResponse;

@Service("employeeService")
public class EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	EmployeeRepository repository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${department.url}")
	String departmentUrl;

	@Value("${organization.url}")
	String organizationUrl;



	public Employee add(Employee employee) {
		LOGGER.info("Employee add: {}", employee);
		return repository.save(employee);
	}

	public EmployeeResponse findById(Long id) {
		EmployeeResponse employeeResponseEntity=new EmployeeResponse();
		LOGGER.info("Employee find: id={}", id);
		Optional<Employee> employeeResponse = repository.findById(id);
		if(employeeResponse.isPresent()) {
			Employee employee=employeeResponse.get();
			employeeResponseEntity=getDepartmentAndOrganizationDetails(employee.getDepartmentId());
			employeeResponseEntity.setEmployeeName(employee.getEmployeeName());
			employeeResponseEntity.setId(employee.getId());
		}
		return employeeResponseEntity;
	}

	public List<EmployeeResponse> findAll() {
		List<EmployeeResponse>employeeResponses=new ArrayList<>();
		LOGGER.info("Employee Department");
		List<Employee> employees=repository.findAll();
		for(Employee employee:employees) {
			EmployeeResponse employeeResponse=getDepartmentAndOrganizationDetails(employee.getDepartmentId());
			employeeResponse.setEmployeeName(employee.getEmployeeName());
			employeeResponse.setId(employee.getId());
			employeeResponses.add(employeeResponse);
		}
		return employeeResponses;
	}

	public EmployeeResponse getDepartmentAndOrganizationDetails(String departmentId) {
		EmployeeResponse employeeResponse=new EmployeeResponse();

		ResponseEntity<DepartmentResponse> departmentResponseEntity  = restTemplate.getForEntity(departmentUrl + departmentId, DepartmentResponse.class);
		DepartmentResponse departmentResponse= departmentResponseEntity.getBody();
		ResponseEntity<OrganizationResponse> organizationResponseEntity = restTemplate.getForEntity(organizationUrl + departmentResponse.getId(), OrganizationResponse.class);
		if(departmentResponse.getDepartmentName()!=null) {
			employeeResponse.setDepartmentName(departmentResponse.getDepartmentName());
		}
		OrganizationResponse organizationAddress=organizationResponseEntity.getBody();
		if(organizationAddress!=null && organizationAddress.getOrganizationAddress()!=null) {
			employeeResponse.setOrganizationAddress(organizationAddress.getOrganizationAddress());
		}
		return employeeResponse;


	}



	public void deleteById(Long id) throws CoreException {
		Optional<Employee> employeeResponse = repository.findById(id);
		if(employeeResponse.isPresent()) {
			repository.deleteById(id);
		}else {
			String message="Resource not found";
			throw new CoreException(message) ;
		}

	}	

	public void updateById(Employee employee,Long id) throws CoreException {
		Optional<Employee> employeeResponse = repository.findById(id);
		if(employeeResponse.isPresent()) {
			repository.save(employee);
		}else {
			String message="Resource not found";
			throw new CoreException(message) ;
		}

	}	
}
