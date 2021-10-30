package com.example.demo.persistence.repository;

import com.example.demo.persistence.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
