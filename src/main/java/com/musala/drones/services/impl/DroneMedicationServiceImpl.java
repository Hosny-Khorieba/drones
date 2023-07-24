package com.musala.drones.services.impl;

import com.musala.drones.enums.DroneStateEnum;
import com.musala.drones.exceptions.DroneBatteryLow;
import com.musala.drones.exceptions.DroneNotExist;
import com.musala.drones.exceptions.DroneWeightLimitExceeded;
import com.musala.drones.exceptions.MedicationNotExist;
import com.musala.drones.models.Drone;
import com.musala.drones.models.Medication;
import com.musala.drones.repositories.DroneRepository;
import com.musala.drones.repositories.MedicationRepository;
import com.musala.drones.services.DroneMedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DroneMedicationServiceImpl implements DroneMedicationService {

    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public void loadDroneWithMedications(Long droneId, List<Long> medicationIds) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new DroneNotExist("Drone ID provided not exist"));

        if(drone.getBatteryCapacity() < 25)
            throw new DroneBatteryLow("Can't load the drone while it's battery capacity lower than 25%");

        AtomicInteger currentWeight = new AtomicInteger();
        medicationIds.forEach(medicationId -> {
            Medication medication = medicationRepository.findById(medicationId).orElseThrow(() -> new MedicationNotExist("Medication ID provided not exist"));
            currentWeight.addAndGet(medication.getWeight());
            if(currentWeight.get() < drone.getWeightLimit()){
                drone.setState(DroneStateEnum.LOADING);
            }else if(currentWeight.get() == drone.getWeightLimit()){
                drone.setState(DroneStateEnum.LOADED);
            }else{
                droneRepository.save(drone);
                throw new DroneWeightLimitExceeded("Drone max weight is " + drone.getWeightLimit() + " grams");
            }
            droneRepository.save(drone);
            medication.setDrone(drone);
            medicationRepository.saveAndFlush(medication);
        });
    }

    @Override
    public List<Medication> getDroneMedications(Long droneId) {
        Drone drone = droneRepository.getById(droneId);
        return medicationRepository.findByDrone(drone);
    }
}
