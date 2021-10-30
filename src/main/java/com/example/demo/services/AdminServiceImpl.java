package com.example.demo.services;

import com.example.demo.persistence.entities.Admin;
import com.example.demo.persistence.entities.Cabin;
import com.example.demo.persistence.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements EntityService<Admin>{
    @Autowired
    AdminRepository adminRepository;

    @Override
    public Admin saveEntity(Admin entity) {
        boolean comply = entity.getEmail().length()<=45 && entity.getPassword().length()<=45 &&entity.getName().length()<=250;
        if(comply){
            return adminRepository.save(entity);
        }
        return new Admin();
    }

    @Override
    public List<Admin> getEntity() {
        return adminRepository.findAll();
    }

    @Override
    public Admin updateEntity(Admin entity) {
        boolean comply = entity.getPassword().length()<=45 && entity.getName().length()<=250;
        Admin admin = adminRepository.findById(entity.getId()).orElse(new Admin("Not updated"));
        if(comply && !admin.getName().equals("Not updated")){
            admin.setName(entity.getName());
            admin.setPassword(entity.getPassword());
            adminRepository.save(admin);
        }
        return admin;
    }

    @Override
    public Admin deleteEntity(Integer id) {
        Admin admin  = adminRepository.findById(id).orElse(new Admin("Not deleted"));
        if (!admin.getName().equals("Not deleted")){
            adminRepository.deleteById(id);
        }
        return admin;
    }
}
