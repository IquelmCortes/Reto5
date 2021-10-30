package com.example.demo.services;

import com.example.demo.persistence.entities.Message;
import com.example.demo.persistence.repository.CabinRepository;
import com.example.demo.persistence.repository.ClientRepository;
import com.example.demo.persistence.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements EntityService<Message>{

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CabinRepository cabinRepository;


    @Override
    public Message saveEntity(Message entity) {
        boolean cumple = entity.getMessageText().length()<= 250;
        if(cumple){
            return messageRepository.save(entity);
        }
        return new Message(null, null, null);
    }

    @Override
    public List<Message> getEntity() {
        return messageRepository.findAll();
    }

    @Override
    public Message updateEntity(Message entity) {
        boolean comply = entity.getMessageText().length()<=250;
        Message message = messageRepository.findById(entity.getIdMessage()).orElse(new Message("Not updated"));
        if(comply && !message.getMessageText().equals("Not updated")){
            message.setMessageText(entity.getMessageText());
            messageRepository.save(message);
        }
        return message;
    }

    @Override
    public Message deleteEntity(Integer id) {
        Message message  = messageRepository.findById(id).orElse(new Message("Not deleted"));
        if (!message.getMessageText().equals("Not deleted")){
            messageRepository.deleteById(id);
        }
        return message;
    }
}
