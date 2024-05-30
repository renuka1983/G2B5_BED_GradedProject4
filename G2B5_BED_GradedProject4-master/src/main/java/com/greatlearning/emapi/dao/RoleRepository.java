package com.greatlearning.emapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.emapi.security.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
