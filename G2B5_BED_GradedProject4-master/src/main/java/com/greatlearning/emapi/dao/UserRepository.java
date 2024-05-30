package com.greatlearning.emapi.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.emapi.security.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);
}
