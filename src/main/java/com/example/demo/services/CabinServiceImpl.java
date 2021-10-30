package com.example.demo.services;

import com.example.demo.persistence.entities.Cabin;
import com.example.demo.persistence.entities.Category;
import com.example.demo.persistence.entities.Message;
import com.example.demo.persistence.entities.Reservation;
import com.example.demo.persistence.repository.CabinRepository;
import com.example.demo.persistence.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CabinServiceImpl implements EntityService<Cabin>{
    @Autowired
    CabinRepository cabinRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Cabin saveEntity(Cabin cabin) {
        boolean cumple = cabin.getBrand().length() <= 45 && cabin.getName().length()<= 45 && cabin.getRooms()>=0 &&
                cabin.getRooms()<=15 && cabin.getDescription().length() <= 250;
        Category category;
        if (cabin.getCategory() !=null) {
            category = cabin.getCategory() == null ? new Category() : cabin.getCategory().getId() == null ?
                    new Category() : categoryRepository.findById(cabin.getCategory().getId()).get();
            cabin.setCategory(category);
        }
        List<Message> messages = new ArrayList<Message>();
        List<Reservation> reservations = new ArrayList<Reservation>();
        if(cumple){
            Cabin cabin1 = cabinRepository.save(cabin);
            return cabin1;
        }
        return new Cabin(null,null, null, null, null,null, null);
    }
    @Override
    public List<Cabin> getEntity() {
        return cabinRepository.findAll();
    }
    @Override
    public Cabin updateEntity(Cabin entity) {
        boolean comply = entity.getBrand().length()<=45 && entity.getName().length() <= 45 && entity.getRooms() >=0&&
                entity.getDescription().length()<=250;
        Cabin cabin = cabinRepository.findById(entity.getId()).orElse(new Cabin ("Not updated"));
        if(comply && !cabin.getDescription().equals("Not updated")){
            cabin.setBrand(entity.getBrand());
            cabin.setName(entity.getName());
            cabin.setRooms(entity.getRooms());
            cabin.setDescription(entity.getDescription());
            cabinRepository.save(cabin);
        }
        return cabin;
    }
    @Override
    public Cabin deleteEntity(Integer id) {
        Cabin cabin  = cabinRepository.findById(id).orElse(new Cabin("Not deleted"));
        boolean comply = cabin.getMessages().isEmpty() && cabin.getReservations().isEmpty();
        if (comply && !cabin.getDescription().equals("Not deleted")){
            cabinRepository.deleteById(id);
        }else{
            cabin.setDescription("Not deleted");
        }
        return cabin;
    }
}
