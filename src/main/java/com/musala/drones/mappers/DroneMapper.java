package com.musala.drones.mappers;

import com.musala.drones.dtos.RegisterDroneDto;
import com.musala.drones.models.Drone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DroneMapper {
    DroneMapper INSTANCE = Mappers.getMapper(DroneMapper.class);

    Drone RegisterDroneDtoToDrone(RegisterDroneDto droneDto);
}
