package com.example.demo.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name="cabin")
public class Cabin {

    @Id
    @SequenceGenerator(name="seq", sequenceName = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String brand;
    @Column
    private Integer rooms;
    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonIgnoreProperties("cabins")
    private Category category;

    @JsonIgnoreProperties({"cabin","client"})
    @OneToMany(cascade=CascadeType.ALL, mappedBy="cabin")
    private List<Message> messages = new ArrayList<>();

    @JsonIgnoreProperties("cabin")
    @OneToMany(cascade=CascadeType.ALL, mappedBy="cabin")
    private List<Reservation> reservations = new ArrayList<>();


    /**
     * @param brand
     * @param name
     * @param rooms
     * @param description
     * @param category
     */
    public Cabin(String brand, String name, Integer rooms, String description, Category category, List<Message> messages,
    List<Reservation> reservations) {
        this.brand = brand;
        this.rooms = rooms;
        //this.category = category;
        this.name = name;
        this.description = description;
        this.messages = messages;
        this.reservations = reservations;
    }
    public Cabin(String brand, String name, Integer rooms, String description ){
        this.brand = brand;
        this.rooms = rooms;
        this.name = name;
        this.description = description;
    }

    public Cabin(String description){
        this.description = description;
    }

    /**
     *
     */
    public Cabin(){}

    /**
     *
     * @param id
     */
    public Cabin(Integer id){
        this.id = id;
    }

    /**
     *
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     *
     * @param rooms
     */
    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    /**
     *
     * @param category
     */

    public void setCategory(Category category) {
        this.category = category;
    }


    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @param messages
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    /**
     *
     * @param reservations
     */
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
