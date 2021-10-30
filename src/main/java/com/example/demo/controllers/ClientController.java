package com.example.demo.controllers;

import com.example.demo.persistence.entities.Cabin;
import com.example.demo.persistence.entities.Client;
import com.example.demo.services.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/Client")
public class ClientController implements EntityController<Client>{

    @Autowired
    ClientServiceImpl clientService;

    @PostMapping(path="save")
    public ResponseEntity<Client> saveEntityContr(@RequestBody Client client){
        Client client1 = clientService.saveEntity(client);
        if (client1.getName()==null && client1.getPassword()==null){
            return new ResponseEntity<>(client1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(client1, HttpStatus.CREATED);
    }

    @GetMapping(path = "all")
    public List<Client> listEntitiesContr(){
        return clientService.getEntity();
    }

    @Override
    @PutMapping(path = "update")
    public ResponseEntity<Client> updateEntityContr(@RequestBody Client entity) {
        Client client = clientService.updateEntity(entity);
        if(client.getName().equals("Not updated")){
            return new ResponseEntity<>(client, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Client> deleteEntityContr(@PathVariable Integer id) {
        Client client = clientService.deleteEntity(id);
        if(client.getName().equals("Not deleted")){
            return new ResponseEntity<>(client, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(client, HttpStatus.NO_CONTENT);
    }
    @GetMapping(path="/report-clients")
    public ArrayList<LinkedHashMap<String,Object>> reportClient(){
        return clientService.reportClient();
    }
}
