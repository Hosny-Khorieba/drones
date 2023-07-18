package com.musala.drones.services.impl;

import com.musala.drones.dtos.RegisterDroneDto;
import com.musala.drones.enums.DroneStateEnum;
import com.musala.drones.mappers.DroneMapper;
import com.musala.drones.models.Drone;
import com.musala.drones.repositories.DroneRepository;
import com.musala.drones.services.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneServiceImpl implements DroneService {

    @Autowired
    private DroneRepository droneRepository;

    @Override
    public Drone register(RegisterDroneDto droneDto) {

        Drone drone = DroneMapper.INSTANCE.RegisterDroneDtoToDrone(droneDto);

        drone.setState(DroneStateEnum.IDLE);

        return droneRepository.save(drone);
    }
}
