package ru.bul.springs.ForTestingOne.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bul.springs.ForTestingOne.models.Person;
import ru.bul.springs.ForTestingOne.models.User;
import ru.bul.springs.ForTestingOne.repository.PersonRepository;
import ru.bul.springs.ForTestingOne.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }
}

