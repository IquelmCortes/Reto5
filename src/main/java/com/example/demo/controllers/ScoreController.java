package com.example.demo.controllers;

import com.example.demo.persistence.entities.Cabin;
import com.example.demo.persistence.entities.Score;
import com.example.demo.services.ScoreServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/Score")
public class ScoreController implements EntityController<Score>{
    private static final Logger logger = LogManager.getLogger(MessageController.class);

    @Autowired
    private ScoreServiceImpl scoreService;

    @PostMapping(path = "/save")
    public ResponseEntity<Score> saveEntityContr(@RequestBody Score score){
        Score score1 = scoreService.saveEntity(score);
        if(score1.getScore() == null ){
            return new ResponseEntity<>(score1, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(score1, HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public List<Score> listEntitiesContr(){
        return scoreService.getEntity();
    }

    @Override
    @PutMapping(path = "update")
    public ResponseEntity<Score> updateEntityContr(@RequestBody Score entity) {
        Score score = scoreService.updateEntity(entity);
        if(score.getMessageScore().equals("Not updated")){
            return new ResponseEntity<>(score, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(score, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Score> deleteEntityContr(@PathVariable Integer id) {
        Score score = scoreService.deleteEntity(id);
        if(score.getMessageScore().equals("Not deleted")){
            return new ResponseEntity<>(score, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(score, HttpStatus.NO_CONTENT);
    }
}
