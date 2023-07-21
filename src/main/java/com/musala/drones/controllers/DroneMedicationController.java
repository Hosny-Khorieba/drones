package com.musala.drones.controllers;

import com.musala.drones.services.DroneMedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DroneMedicationController {

    @Autowired
    private DroneMedicationService droneMedicationService;

    @PutMapping(value = "drone/load")
    public ResponseEntity<?> loadDroneWithMedications(@RequestParam Long droneId, @RequestParam List<Long> medicationIds){
        droneMedicationService.loadDroneWithMedications(droneId, medicationIds);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "medications/{droneId}")
    public ResponseEntity<?> getDroneMedications(@PathVariable Long droneId){
        return ResponseEntity.ok(droneMedicationService.getDroneMedications(droneId));
    }
}
