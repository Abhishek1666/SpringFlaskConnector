package com.diseasepredictor.detection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diseasepredictor.detection.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}