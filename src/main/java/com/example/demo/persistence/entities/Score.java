package com.example.demo.persistence.entities;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity(name = "score")
@Table(name = "score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idScore;
    private Integer Score;
    private String messageScore;

    @ManyToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "idReservation")
    private Reservation reservation;

    public Score(){ }

    public Score(Integer score, String messageScore, Reservation reservation) {
        Score = score;
        this.messageScore = messageScore;
        this.reservation = reservation;
    }

    public Score(String messageScore){
        this.messageScore = messageScore;
    }

    public void setScore(Integer score) {
        Score = score;
    }

    public void setMessageScore(String messageScore) {
        this.messageScore = messageScore;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
