package com.example.demo.services;

import com.example.demo.persistence.entities.Cabin;
import com.example.demo.persistence.entities.Category;
import com.example.demo.persistence.repository.CabinRepository;
import com.example.demo.persistence.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements EntityService<Category>{
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CabinRepository cabinRepository;

    @Override
    public Category saveEntity(Category entity) {
        boolean cumple = entity.getDescription().length()<=250 && entity.getName().length() <=45;
        List<Cabin> cabins = new ArrayList<Cabin>();
        entity.setCabins(cabins);
        if (cumple){
            return categoryRepository.save(entity);
        }

        return new Category(null, null, null);
    }

    @Override
    public List<Category> getEntity() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateEntity(Category entity) {
        boolean comply = entity.getDescription().length()<=250 && entity.getName().length()<=45;
        Category category = categoryRepository.findById(entity.getId()).orElse(new Category("Not updated"));
        if(comply && !category.getDescription().equals("Not updated")){
            category.setDescription(entity.getDescription());
            category.setName(entity.getName());
            categoryRepository.save(category);
        }
        return category;
    }

    @Override
    public Category deleteEntity(Integer id) {
        Category category  = categoryRepository.findById(id).orElse(new Category("Not deleted"));
        boolean comply = category.getCabins().isEmpty();
        if (comply && !category.getDescription().equals("Not deleted")){
            categoryRepository.deleteById(id);
        }else{
            category.setDescription("Not deleted");
        }
        return category;
    }
}
