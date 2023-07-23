package com.musala.drones.controllers;

import com.musala.drones.dtos.RegisterDroneDto;
import com.musala.drones.services.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/drone")
public class DroneController {

    @Autowired
    private DroneService droneService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDroneDto droneDto){
        return ResponseEntity.ok(droneService.register(droneDto));
    }

    @GetMapping(value = "/available-for-loading")
    public ResponseEntity<?> getAvailableForLoading(){
        return ResponseEntity.ok(droneService.getAvailableForLoading());
    }

    @GetMapping(value = "/{droneId}/battery-percentage")
    public ResponseEntity<?> getBatteryPercentage(@PathVariable Long droneId){
        return ResponseEntity.ok(droneService.getBatteryPercentage(droneId));
    }
}
