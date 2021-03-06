package com.example.organization.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IBaseJpaRepository<I extends Serializable,E>
	extends JpaRepository<E, I> {
	
}
