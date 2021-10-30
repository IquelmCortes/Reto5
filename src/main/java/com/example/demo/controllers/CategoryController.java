package com.example.demo.controllers;

import com.example.demo.persistence.entities.Admin;
import com.example.demo.persistence.entities.Cabin;
import com.example.demo.persistence.entities.Category;
import com.example.demo.services.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/Category")
public class CategoryController implements EntityController<Category>{

    @Autowired
    CategoryServiceImpl categoryService;

    @PostMapping(path = "/save")
    public ResponseEntity<Category> saveEntityContr(@RequestBody Category category){
        Category category1 = categoryService.saveEntity(category);
        if (category1.getName() == null){
            return new ResponseEntity<>(category1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(category1, HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public List<Category> listEntitiesContr(){
        return categoryService.getEntity();
    }

    @Override
    @PutMapping(path = "update")
    public ResponseEntity<Category> updateEntityContr(@RequestBody Category entity) {
        Category category = categoryService.updateEntity(entity);
        if(category.getDescription().equals("Not updated")){
            return new ResponseEntity<>(category, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Category> deleteEntityContr(@PathVariable Integer id) {
        Category category = categoryService.deleteEntity(id);
        if(category.getDescription().equals("Not deleted")){
            return new ResponseEntity<>(category, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(category, HttpStatus.NO_CONTENT);
    }
}
