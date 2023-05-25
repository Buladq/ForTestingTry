package ru.bul.springs.ForTestingOne.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bul.springs.ForTestingOne.models.Person;
import ru.bul.springs.ForTestingOne.service.PersonService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons(){
        return new ResponseEntity<List<Person>>(personService.getAllPersons(),
                HttpStatus.OK);
    }
}
