package ru.bul.springs.ForTestingOne.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bul.springs.ForTestingOne.models.Position;
import ru.bul.springs.ForTestingOne.repository.PositionRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PositionService {

    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<Position> getAll(){
        return positionRepository.findAll();
    }

    @Transactional
    public void addPosition(Position position){
        positionRepository.save(position);
    }
    @Transactional
    public void deletePosition(Position position){
        positionRepository.delete(position);
    }

    @Transactional
    public void updatePosition(int id,Position position){
        position.setId(id);
        positionRepository.save(position);

    }


}
