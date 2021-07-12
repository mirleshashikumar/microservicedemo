package com.example.organization.repository;

import org.springframework.stereotype.Repository;

import com.example.organization.model.Organization;


@Repository
public interface OrganitzationRepository extends IBaseJpaRepository<Long,Organization> {

	
}
