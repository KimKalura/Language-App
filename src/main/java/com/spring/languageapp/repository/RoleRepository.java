package com.spring.languageapp.repository;

import com.spring.languageapp.model.Role;
import com.spring.languageapp.model.RoleType;
import com.spring.languageapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleType(RoleType roleType);
}