package com.example.demo.persistence.repository;

import com.example.demo.persistence.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Integer> {
}
