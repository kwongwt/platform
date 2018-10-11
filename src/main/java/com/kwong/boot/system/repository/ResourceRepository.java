package com.kwong.boot.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kwong.boot.system.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource,Integer>{
	
}
