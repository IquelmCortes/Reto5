package com.example.demo.controllers;

import com.example.demo.persistence.entities.Cabin;
import com.example.demo.services.CabinServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/Cabin")
public class CabinController implements EntityController<Cabin>{

    @Autowired
    private CabinServiceImpl cabinService;

    @PostMapping(path="/save")
    public ResponseEntity<Cabin> saveEntityContr(@RequestBody Cabin cabin){
        Cabin cabinSaved = cabinService.saveEntity(cabin);
        if(cabinSaved.getRooms() == null || cabinSaved.getBrand() == null){
            return new ResponseEntity<>(cabinSaved, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cabinSaved, HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public List<Cabin> listEntitiesContr(){
        return cabinService.getEntity();
    }

    @Override
    @PutMapping(path = "update")
    public ResponseEntity<Cabin> updateEntityContr(@RequestBody Cabin entity) {
        Cabin cabin = cabinService.updateEntity(entity);
        if(cabin.getDescription().equals("Not updated")){
            return new ResponseEntity<>(cabin, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cabin, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Cabin> deleteEntityContr(@PathVariable Integer id) {
        Cabin cabin = cabinService.deleteEntity(id);
        if(cabin.getDescription().equals("Not deleted")){
            return new ResponseEntity<>(cabin, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cabin, HttpStatus.NO_CONTENT);
    }
}
