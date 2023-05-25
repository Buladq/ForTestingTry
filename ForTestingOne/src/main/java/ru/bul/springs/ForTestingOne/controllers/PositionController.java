package ru.bul.springs.ForTestingOne.controllers;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bul.springs.ForTestingOne.models.Position;
import ru.bul.springs.ForTestingOne.service.PositionService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }



    @PostMapping("/add")
    public ResponseEntity<HttpStatus> createPosition(@RequestBody @Valid Position position,BindingResult bindingResult){
        positionService.addPosition(position);
        return ResponseEntity.ok(HttpStatus.CREATED);

    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updatePosition(@RequestBody @Valid Position position,BindingResult bindingResult,
                                                     @PathVariable("id")Integer id ){
        positionService.updatePosition(id,position);
        return ResponseEntity.ok(HttpStatus.OK);


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePosition(@PathVariable("id")Integer id){
        positionService.deletePosition(id);
        return new ResponseEntity<>("Position successfully deleted!", HttpStatus.OK);


    }



    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationException(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.joining("; "));
        return ResponseEntity.badRequest().body(message);
    }
}
