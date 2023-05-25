package ru.bul.springs.ForTestingOne.service;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bul.springs.ForTestingOne.models.User;
import ru.bul.springs.ForTestingOne.repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;



    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public List<User> getByPosition(String position){
        return userRepository.getUserByPosition(position);
    }

    @Transactional
    public void adduser(User user){
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(int id){
        Optional<User> userForDelete=userRepository.findById(id);
        userRepository.deleteById(userForDelete.get().getId());
    }

    @Transactional
    public void updateUser(int id,User user){
       Optional<User> userToBeUpdated=userRepository.findById(id);
       user.setId(userToBeUpdated.get().getId());
       userRepository.save(user);
    }


    public List<User> getByDateOfBirth(LocalDate localDate) {
        return userRepository.getByDateOfBirth(localDate);
    }
}
