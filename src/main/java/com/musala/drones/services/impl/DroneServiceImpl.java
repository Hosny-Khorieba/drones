package com.musala.drones.services.impl;

import com.musala.drones.dtos.RegisterDroneDto;
import com.musala.drones.enums.DroneStateEnum;
import com.musala.drones.exceptions.DroneNotExist;
import com.musala.drones.mappers.DroneMapper;
import com.musala.drones.models.Drone;
import com.musala.drones.repositories.DroneRepository;
import com.musala.drones.services.DroneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
@Slf4j
public class DroneServiceImpl implements DroneService {

    @Autowired
    private DroneRepository droneRepository;

    @Override
    public Drone register(RegisterDroneDto droneDto) {

        Drone drone = DroneMapper.INSTANCE.RegisterDroneDtoToDrone(droneDto);

        drone.setState(DroneStateEnum.IDLE);

        return droneRepository.save(drone);
    }

    @Override
    public List<Drone> getAvailableForLoading() {
        return droneRepository.findByStateAndBatteryCapacityGreaterThanEqual(DroneStateEnum.IDLE, 25);
    }

    @Override
    public Integer getBatteryPercentage(Long droneId) {
        Integer batteryPercentage = droneRepository.getBatteryPercentageForDrone(droneId);
        if(batteryPercentage != null){
            return batteryPercentage;
        }
        throw new DroneNotExist("Provided drone not exist");
    }

    @Scheduled(fixedRate = 1000)
    public void checkAndLogDronesBattery(){
        List<Drone> drones = droneRepository.findAll();
        drones.forEach(drone -> {
            log.info("Battery Status for Drone: " + drone.getSerialNum() + " at " + Calendar.getInstance().getTime() + " is " + drone.getBatteryCapacity() + "%");
        });
    }

    @Scheduled(fixedRate = 1500)
    public void ManipulateDronesBattery(){
        List<Drone> drones = droneRepository.findAll();
        drones.forEach(drone -> {
            if(drone.getBatteryCapacity() > 20){
                drone.setBatteryCapacity(drone.getBatteryCapacity() - 1);
            }else{
                drone.setBatteryCapacity(100);
            }
            droneRepository.save(drone);
        });
    }
}
