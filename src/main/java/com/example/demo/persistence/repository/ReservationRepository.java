package com.example.demo.persistence.repository;

import com.example.demo.persistence.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    //public List<Reservation> findByStartDateAndEndDateBefore(Date startDate, Date endDate);
}
