package com.kwong.boot.system.repository;

import java.util.List;

import javax.annotation.Resources;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kwong.boot.system.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
