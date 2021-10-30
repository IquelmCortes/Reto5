package com.example.demo.controllers;

import com.example.demo.persistence.entities.Cabin;
import com.example.demo.persistence.entities.Message;
import com.example.demo.services.MessageServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/Message")
public class MessageController implements EntityController<Message>{

    @Autowired
    MessageServiceImpl messageService;

    private static final Logger logger = LogManager.getLogger(MessageController.class);

    @PostMapping(path = "/save")
    public ResponseEntity<Message> saveEntityContr(@RequestBody Message message){
        Message message1 = messageService.saveEntity(message);
        if(message1.getMessageText() == null ){
            return new ResponseEntity<>(message1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message1, HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public List<Message> listEntitiesContr(){
        return messageService.getEntity();
    }

    @Override
    @PutMapping(path = "update")
    public ResponseEntity<Message> updateEntityContr(@RequestBody Message entity) {
        Message message = messageService.updateEntity(entity);
        if(message.getMessageText().equals("Not updated")){
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Message> deleteEntityContr(@PathVariable Integer id) {
        Message message = messageService.deleteEntity(id);
        if(message.getMessageText().equals("Not deleted")){
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
    }
}
