package ru.bul.springs.ForTestingOne.controllers;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.bul.springs.ForTestingOne.models.Position;
import ru.bul.springs.ForTestingOne.service.PositionService;
import ru.bul.springs.ForTestingOne.util.PositionErrorResponse;
import ru.bul.springs.ForTestingOne.util.PositionNotCreatedException;
import ru.bul.springs.ForTestingOne.util.PositionNotUpdatedException;

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
        if(bindingResult.hasErrors()) {
            StringBuilder erMsg = new StringBuilder();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            for (var i :
                    errorList) {
                erMsg.append(i.getField());
                erMsg.append("-").append(i.getDefaultMessage());
                erMsg.append(";");

            }
            throw new PositionNotCreatedException(erMsg.toString());
        }
        positionService.addPosition(position);
        return ResponseEntity.ok(HttpStatus.CREATED);

    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updatePosition(@RequestBody @Valid Position position,BindingResult bindingResult,
                                                     @PathVariable("id")Integer id ){
        if(bindingResult.hasErrors()) {
            StringBuilder erMsg = new StringBuilder();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            for (var i :
                    errorList) {
                erMsg.append(i.getField());
                erMsg.append("-").append(i.getDefaultMessage());
                erMsg.append(";");

            }
            throw new PositionNotUpdatedException(erMsg.toString());
        }
        positionService.updatePosition(id,position);
        return ResponseEntity.ok(HttpStatus.OK);


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePosition(@PathVariable("id")Integer id){
        positionService.deletePosition(id);
        return new ResponseEntity<>("Position successfully deleted!", HttpStatus.OK);


    }


    @ExceptionHandler
    private ResponseEntity<PositionErrorResponse> handleException(PositionNotCreatedException positionNotCreatedException){
        PositionErrorResponse positionErrorResponse=new PositionErrorResponse(
                positionNotCreatedException.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(positionErrorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<PositionErrorResponse> handleException(PositionNotUpdatedException positionNotUpdatedException){
        PositionErrorResponse positionErrorResponse=new PositionErrorResponse(
                positionNotUpdatedException.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(positionErrorResponse,HttpStatus.BAD_REQUEST);
    }




}
