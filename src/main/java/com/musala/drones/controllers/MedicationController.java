package com.musala.drones.controllers;

import com.musala.drones.dtos.RegisterMedicationDto;
import com.musala.drones.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/medication")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestPart("image") MultipartFile image, @RequestPart("medicationDto") RegisterMedicationDto medicationDto){
        return ResponseEntity.ok(medicationService.register(image, medicationDto));
    }
}
