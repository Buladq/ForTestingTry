package ru.bul.springs.ForTestingOne.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bul.springs.ForTestingOne.models.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position,Integer> {
}
