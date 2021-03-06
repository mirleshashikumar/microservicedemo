package com.example.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.department.model.Department;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, Long> {

	
}
