package com.example.demo.services;

import com.example.demo.persistence.entities.Cabin;
import com.example.demo.persistence.entities.Score;
import com.example.demo.persistence.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements EntityService<Score>{
    @Autowired
    ScoreRepository scoreRepository;

    @Override
    public Score saveEntity(Score entity) {
        boolean comply = entity.getScore() <=5 && entity.getScore() >=0 && entity.getMessageScore().length() <=250;
        if(comply){
            return scoreRepository.save(entity);
        }
        return new Score();
    }

    @Override
    public List<Score> getEntity() {
        return scoreRepository.findAll();
    }

    @Override
    public Score updateEntity(Score entity) {
        boolean comply = entity.getScore()%2 == 0 && entity.getScore()>=0 && entity.getScore()<=5 && entity.getMessageScore().length() <= 250;
        Score score = scoreRepository.findById(entity.getIdScore()).orElse(new Score("Not updated"));
        if(comply && !score.getMessageScore().equals("Not updated")){
            score.setScore(entity.getScore());
            score.setMessageScore(entity.getMessageScore());
            scoreRepository.save(score);
        }
        return score;
    }

    @Override
    public Score deleteEntity(Integer id) {
        Score score  = scoreRepository.findById(id).orElse(new Score("Not deleted"));
        if (!score.getMessageScore().equals("Not deleted")){
            scoreRepository.deleteById(id);
        }
        return score;
    }
}
