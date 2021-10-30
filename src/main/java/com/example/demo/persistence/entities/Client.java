package com.example.demo.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "client")
@Getter
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClient;
    @Column(name = "correo")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "nombre")
    private String name;
    @Column(name = "edad")
    private Integer age;

    @JsonIgnoreProperties({"client"})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<Message> messages;

    @JsonIgnoreProperties({"client"})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<Reservation> reservations;

    public Client(){
    }

    public Client(String name){
        this.name = name;
    }

    public Client(Integer id){
        this.idClient = id;
    }
    
    public Client(String name, String email, Integer age, String password){
        this.name = name;
        this.email = email;
        this.age = age;
        this.password =password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
