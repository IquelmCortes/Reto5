package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface EntityController<t> {

    @PostMapping(path = "/save")
    public ResponseEntity<t> saveEntityContr(@RequestBody t entity);

    @GetMapping(path = "/all")
    public List<t> listEntitiesContr();

    @PutMapping(path="update")
    public ResponseEntity<t> updateEntityContr(@RequestBody t entity);

    @DeleteMapping(path = "/:id")
    public ResponseEntity<t> deleteEntityContr(@PathVariable Integer id);
}
