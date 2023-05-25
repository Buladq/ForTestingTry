package ru.bul.springs.ForTestingOne.service;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bul.springs.ForTestingOne.models.User;
import ru.bul.springs.ForTestingOne.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(boolean sortByAge){
        if(sortByAge){
            return userRepository.findAll(Sort.by(Sort.Direction.ASC,"dateOfBirth"));
        }
        return userRepository.findAll();
    }
}
