package com.example.demo.persistence.repository;

import com.example.demo.persistence.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Integer> {

}
