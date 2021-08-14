package com.barcodegenerator.barcodegenerator.persistence.repository;

import com.barcodegenerator.barcodegenerator.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByUsername(String username);
}
