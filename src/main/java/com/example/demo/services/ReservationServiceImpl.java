package com.example.demo.services;

import com.example.demo.persistence.entities.Cabin;
import com.example.demo.persistence.entities.Client;
import com.example.demo.persistence.entities.Reservation;
import com.example.demo.persistence.repository.CabinRepository;
import com.example.demo.persistence.repository.ClientRepository;
import com.example.demo.persistence.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationServiceImpl implements EntityService<Reservation>{
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CabinRepository cabinRepository;


    @Override
    public Reservation saveEntity(Reservation entity) {
        Date currentDate = new Date();
        Client client = clientRepository.findById(entity.getClient().getIdClient()).get();
        Cabin cabin = cabinRepository.findById(entity.getCabin().getId()).get();
        entity.setStatus(entity.getStatus());
        entity.setCreatedDate(currentDate);
        entity.setCabin(cabin);
        entity.setClient(client);
        return reservationRepository.save(entity);
    }

    @Override
    public List<Reservation> getEntity() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation updateEntity(Reservation entity) {
        boolean comply = entity.getStatus() == "Programado"||entity.getStatus()== "Cancelado" || entity.getStatus() == "Realizado";
        Reservation reservation = reservationRepository.findById(entity.getIdReservation()).orElse(new Reservation("Not updated"));
        try{
            Date startDate = entity.getStartDate();
            Date endDate = entity.getDevolutionDate();
            comply = comply && Math.abs(endDate.getDay()-startDate.getDay())>=1;
        }catch (Exception e){
            comply = false;
        }
        if(comply && !reservation.getStatus().equals("Not updated")){
            reservation.setStatus(entity.getStatus());
            reservation.setDevolutionDate(entity.getDevolutionDate());
            reservation.setStartDate(entity.getStartDate());
            reservationRepository.save(reservation);
        }
        return reservation;
    }

    @Override
    public Reservation deleteEntity(Integer id) {
        Reservation reservation  = reservationRepository.findById(id).orElse(new Reservation("Not deleted"));
        if (!reservation.getStatus().equals("Not deleted")){
            reservationRepository.deleteById(id);
        }
        return reservation;
    }

    public List<Reservation> findByDates(Date startDate, Date endDate){
        List<Reservation> reservations =  reservationRepository.findAll();
        ArrayList<Reservation> reservationsToSend= new ArrayList<>();
        reservations.forEach(
                reservation -> {
                    if (reservation.getStartDate().after(startDate) && reservation.getStartDate().before(endDate)) {
                        reservationsToSend.add(reservation);
                    }
                }
        );
        return reservationsToSend;
    }

    public LinkedHashMap<String, Integer> findByStatus(){
        List<Reservation> listOfReservations =  reservationRepository.findAll();
        LinkedHashMap<String, Integer> status = new LinkedHashMap<>();
        Integer counterCompleted = 0;
        Integer counterCancelled = 0;
        for (Reservation reser : listOfReservations) {
            String str= reser.getStatus();
            if (str.equals("completed")) {
                counterCompleted+=1;
            } else if (str.equals("cancelled")) {
                    counterCancelled+=1;
            }
        }
        status.put("completed", counterCompleted);
        status.put("cancelled", counterCancelled);
        return status;
    }
}
