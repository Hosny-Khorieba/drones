package com.musala.drones.services;

import com.musala.drones.dtos.RegisterDroneDto;
import com.musala.drones.models.Drone;

public interface DroneService {
    public Drone register(RegisterDroneDto droneDto);
}
