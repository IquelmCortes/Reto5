package com.example.demo.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @SequenceGenerator(name="seq", sequenceName = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReservation;
    private Date startDate;
    private Date devolutionDate;
    private String status;
    @JsonIgnore
    private Date createdDate;

    @JsonIgnoreProperties({"reservations","client"})
    @ManyToOne
    @JoinColumn(name="cabinId")
    private Cabin cabin;

    @JsonIgnoreProperties({"reservations","messages"})
    @ManyToOne
    @JoinColumn(name="idClient")
    private Client client;

    private String score;

    public Reservation() {
    }

    public Reservation(Date startDate, Date devolutionDate, String status, Date createdDate, Client client, Cabin cabin) {
        this.startDate = startDate;
        this.devolutionDate = devolutionDate;
        this.status = status;
        this.createdDate = createdDate;
        this.client = client;
        this.cabin = cabin;
    }

    public Reservation(String status){
        this.status = status;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }

    public void setDevolutionDate(Date devolutionDate) {
        this.devolutionDate = devolutionDate;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
