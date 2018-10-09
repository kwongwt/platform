package com.kwong.boot.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kwong.boot.system.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	/**
     * 通过账号获取用户
     */
	User findByUsername(String username);

	List<User> findByUsernameAndPassword(String username, String password);
}
