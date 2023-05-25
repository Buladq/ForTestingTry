package ru.bul.springs.ForTestingOne.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.bul.springs.ForTestingOne.models.User;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "select \"user\".id,\"user\".dateofbirth,\"user\".email,\"user\".fullname,\"user\".phonenumber,\"user\".position_id from \"user\" join position p on p.id = \"user\".position_id where p.name=:positionName",nativeQuery = true)
    public List<User> getUserByPosition(@Param("positionName")String positionName);

    @Query(value = "select *from \"user\" where dateofbirth=:dateOfBirth ORDER BY dateofbirth ASC",nativeQuery = true )
    public List<User> getByDateOfBirth(@Param("dateOfBirth")LocalDate dateOfBirth);
}
