package com.example.demo.persistence.entities;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity(name = "admin")
@Table(name ="admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String name;
    private String email;
    private String password;

    public Admin(){ }

    public Admin(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Admin(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
