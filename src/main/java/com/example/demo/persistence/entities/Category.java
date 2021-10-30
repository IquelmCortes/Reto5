package com.example.demo.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "category_id")
public class Category {

    @Id
    @SequenceGenerator(name="seq", sequenceName = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name="description")
    private String description;

    //
    @OneToMany(cascade = CascadeType.MERGE,mappedBy="category")
    @JsonIgnoreProperties("category")
    private List<Cabin> cabins = new ArrayList<>();


    public Category(String name, String description, List<Cabin> cabins) {
        this.name = name;
        this.description = description;
        this.cabins = cabins;
    }

    public Category(){}

    public Category(String description){
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCabins(List<Cabin> cabins) {
        this.cabins = cabins;
    }
}
