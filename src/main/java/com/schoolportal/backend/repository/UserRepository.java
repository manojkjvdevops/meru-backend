package com.schoolportal.backend.repository;

import com.schoolportal.backend.model.User;
import com.schoolportal.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    int countByRole(Role role);
    int countByRoleAndActive(Role role, boolean active);
    
    // NEW: Used by the authorization mechanism to identify matching login context entities
    Optional<User> findByEmail(String email);
}