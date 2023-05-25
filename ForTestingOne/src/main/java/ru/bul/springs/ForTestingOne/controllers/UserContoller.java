package ru.bul.springs.ForTestingOne.controllers;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bul.springs.ForTestingOne.models.Position;
import ru.bul.springs.ForTestingOne.models.User;
import ru.bul.springs.ForTestingOne.service.UserService;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/users")
@RestController
public class UserContoller {

    private final UserService userService;

    public UserContoller(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/getbyposition/{position}")
    public ResponseEntity<List<User>> allUsersByPosition(@PathVariable("position")String position){
        return new ResponseEntity<List<User>>(userService.getByPosition(position),
                HttpStatus.OK);
    }

    @GetMapping("/getbydateofbirth/{dateofbirth}")
    public ResponseEntity<List<User>> allUsersByDate(@PathVariable("dateofbirth")LocalDate dateofbirth){
        return new ResponseEntity<List<User>>(userService.getByDateOfBirth(dateofbirth),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id")Integer id){
        userService.deleteUser(id);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);

    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid User user, BindingResult bindingResult){
            userService.adduser(user);
            return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody @Valid User user, BindingResult bindingResult,
                                                     @PathVariable("id")Integer id ){
        userService.updateUser(id,user);
        return ResponseEntity.ok(HttpStatus.OK);


    }





    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationException(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.joining("; "));
        return ResponseEntity.badRequest().body(message);
    }

}
