package com.example.demo.persistence.repository;

import com.example.demo.persistence.entities.Cabin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabinRepository extends JpaRepository<Cabin, Integer> {

}
