package com.example.demo.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "messsage")
@Getter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMessage;
    @Column(name = "text_message")
    private String messageText;

    @JsonIgnoreProperties({"messages","reservations","client"})
    @ManyToOne
    @JoinColumn(name="cabinId")
    private Cabin cabin;

    @JsonIgnoreProperties({"messages","reservations"})
    @ManyToOne
    @JoinColumn(name="idClient")
    private Client client;

    public Message() {
    }

    public Message(String messageText) {
        this.messageText = messageText;
    }

    public Message(String messageText, Client client, Cabin cabin){
        this.messageText = messageText;
        this.client = client;
        this.cabin = cabin;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
