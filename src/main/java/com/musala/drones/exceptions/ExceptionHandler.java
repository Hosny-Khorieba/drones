package com.musala.drones.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.musala.drones.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(DroneNotExist.class)
    public ResponseEntity<?> handleDroneNotExist(RuntimeException exception){
        return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), new Date(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DroneWeightLimitExceeded.class)
    public ResponseEntity<?> handleDroneWeightLimitExceeded(RuntimeException exception){
        return new ResponseEntity<>(new ErrorDto(HttpStatus.BAD_REQUEST.value(), new Date(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MedicationNotExist.class)
    public ResponseEntity<?> handleMedicationNotExist(RuntimeException exception){
        return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), new Date(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<?> handleInvalidFormatException(InvalidFormatException e) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), e.getMessage()), HttpStatus. NOT_ACCEPTABLE);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), e.getMessage()), HttpStatus. NOT_ACCEPTABLE);
    }
}
