package com.barcodegenerator.barcodegenerator.persistence.repository;

import com.barcodegenerator.barcodegenerator.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> getRoleByName(String name);
}
