package com.musala.drones.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.musala.drones.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(DroneNotExist.class)
    public ResponseEntity<?> handleDroneNotExist(RuntimeException exception){
        return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), new Date(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DroneBatteryLow.class)
    public ResponseEntity<?> handleDroneBatteryLow(RuntimeException exception){
        return new ResponseEntity<>(new ErrorDto(HttpStatus.BAD_REQUEST.value(), new Date(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DroneWeightLimitExceeded.class)
    public ResponseEntity<?> handleDroneWeightLimitExceeded(RuntimeException exception){
        return new ResponseEntity<>(new ErrorDto(HttpStatus.BAD_REQUEST.value(), new Date(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MedicationNotExist.class)
    public ResponseEntity<?> handleMedicationNotExist(RuntimeException exception){
        return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), new Date(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<?> handleInvalidFormatException(InvalidFormatException e) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), e.getMessage()), HttpStatus. NOT_ACCEPTABLE);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), e.getMessage()), HttpStatus. NOT_ACCEPTABLE);
    }
}
