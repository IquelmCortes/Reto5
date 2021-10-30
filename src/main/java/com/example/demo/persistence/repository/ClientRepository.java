package com.example.demo.persistence.repository;


import com.example.demo.persistence.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
