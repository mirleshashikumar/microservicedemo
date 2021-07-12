package com.example.organization.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.organization.model.CoreException;
import com.example.organization.model.Organization;
import com.example.organization.repository.OrganitzationRepository;


@Service("organizationService")
public class OrganizationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationService.class);

	@Autowired
	OrganitzationRepository repository;
		
	
	public Organization add(Organization organization) {
		LOGGER.info("organization add: {}", organization);
		return repository.save(organization);
	}
	
	public Organization findById(Long id) {
		Organization organization=new Organization();
		LOGGER.info("organization find: id={}", id);
		 Optional<Organization> organizationResponse = repository.findById(id);
		 if(organizationResponse.isPresent()) {
			 organization=organizationResponse.get();
		 }
		 return organization;
	}
	
	public List<Organization> findAll() {
		LOGGER.info("organization Department");
		return repository.findAll();
	}
	
	public void deleteById(Long id) throws CoreException {
		 Optional<Organization> organizationResponse = repository.findById(id);
		 if(organizationResponse.isPresent()) {
			 repository.deleteById(id);
		 }else {
			 String message="Resource not found";
		 throw new CoreException(message) ;
		 }
		
	}	
	public Organization updateById(Organization organization,Long id) throws CoreException {
		 Optional<Organization> organizationResponse = repository.findById(id);
		 if(organizationResponse.isPresent()) {
			 repository.save(organization);
		 }else {
			 String message="Resource not found";
		 throw new CoreException(message) ;
		 }
	return organizationResponse.get();	
	}	
}
