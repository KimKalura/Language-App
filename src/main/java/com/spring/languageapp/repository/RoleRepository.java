package com.spring.languageapp.repository;

import com.spring.languageapp.model.Role;
import com.spring.languageapp.model.RoleType;
import com.spring.languageapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleType(RoleType roleType);
}