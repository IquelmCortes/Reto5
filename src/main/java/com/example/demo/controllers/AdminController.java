package com.example.demo.controllers;

import com.example.demo.persistence.entities.Admin;
import com.example.demo.services.AdminServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/Admin")
public class AdminController implements EntityController<Admin> {
    private static final Logger logger = LogManager.getLogger(MessageController.class);

    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping(path = "/save")
    public ResponseEntity<Admin> saveEntityContr(@RequestBody Admin admin){
        Admin admin1 = adminService.saveEntity(admin);
        if(admin1.getPassword() == null ){
            return new ResponseEntity<>(admin1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(admin1, HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public List<Admin> listEntitiesContr(){
        return adminService.getEntity();
    }

    @Override
    @PutMapping(path = "update")
    public ResponseEntity<Admin> updateEntityContr(@RequestBody Admin entity) {
        Admin admin = adminService.updateEntity(entity);
        if(admin.getName().equals("Not updated")){
            return new ResponseEntity<>(admin, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Admin> deleteEntityContr(@PathVariable Integer id) {
        Admin admin = adminService.deleteEntity(id);
        if(admin.getName().equals("Not deleted")){
            return new ResponseEntity<>(admin, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(admin, HttpStatus.NO_CONTENT);
    }
}
