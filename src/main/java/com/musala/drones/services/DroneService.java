package com.musala.drones.services;

import com.musala.drones.dtos.RegisterDroneDto;
import com.musala.drones.models.Drone;

import java.util.List;

public interface DroneService {
    public Drone register(RegisterDroneDto droneDto);

    List<Drone> getAvailableForLoading();

    Integer getBatteryPercentage(Long droneId);
}
