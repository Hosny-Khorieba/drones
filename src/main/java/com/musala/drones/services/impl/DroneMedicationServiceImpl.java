package com.musala.drones.services.impl;

import com.musala.drones.models.Drone;
import com.musala.drones.models.Medication;
import com.musala.drones.repositories.DroneRepository;
import com.musala.drones.repositories.MedicationRepository;
import com.musala.drones.services.DroneMedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneMedicationServiceImpl implements DroneMedicationService {

    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public void loadDroneWithMedications(Long droneId, List<Long> medicationIds) {
        //TODO make sure to throw exception if drone not exist
        Drone drone = droneRepository.findById(droneId).get();
        medicationIds.forEach(medicationId -> {
            //TODO make sure to throw exception if medication not exist
            Medication medication = medicationRepository.getById(medicationId);
            medication.setDrone(drone);
            medicationRepository.saveAndFlush(medication);
        });
    }
}
