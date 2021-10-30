package com.example.demo.services;

import com.example.demo.persistence.entities.Client;
import com.example.demo.persistence.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ClientServiceImpl implements EntityService<Client>{
    @Autowired
    ClientRepository clientRepository;
    @Override
    public Client saveEntity(Client entity) {
        boolean cumple = entity.getPassword().length() <=45 && entity.getEmail().length()<=45&&
                entity.getAge()<=130 && entity.getAge()>=0 && entity.getName().length()<=250;
        if(cumple){
            return clientRepository.save(entity);
        }
        return new Client(null, null,null,null);
    }
    @Override
    public List<Client> getEntity() {
        return clientRepository.findAll();
    }
    @Override
    public Client updateEntity(Client entity) {
        boolean comply = entity.getPassword().length() <= 45 && entity.getName().length()<=250 && entity.getAge() <= 10000;
        Client client = clientRepository.findById(entity.getIdClient()).orElse(new Client("Not updated"));
        if(comply && !client.getName().equals("Not updated")){
            client.setName(entity.getName());
            client.setAge(entity.getAge());
            client.setPassword(entity.getPassword());
            clientRepository.save(client);
        }
        return client;
    }
    @Override
    public Client deleteEntity(Integer id) {
        Client client  = clientRepository.findById(id).orElse(new Client("Not deleted"));
        if (!client.getName().equals("Not deleted")){
            clientRepository.deleteById(id);
        }
        return client;
    }
    public ArrayList<LinkedHashMap<String, Object>> reportClient(){
        List<Client> clients = clientRepository.findAll();
        final Comparator<Client> clientComparator = new Comparator<Client>() {
            @Override public int compare(Client t1, Client t2) {
                return t2.getReservations().size()-t1.getReservations().size();
            }
        };
        Collections.sort(clients,clientComparator);
        ArrayList<LinkedHashMap<String, Object>> report = new ArrayList<>();
        for(Client client : clients){
            report.add(new LinkedHashMap(){{
                put("total", client.getReservations().size());
                put("client", client);
            }});
        }
        return report;
    }
}
