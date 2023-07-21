package com.musala.drones.services;

import com.musala.drones.models.Medication;

import java.util.List;

public interface DroneMedicationService {
    void loadDroneWithMedications(Long droneId, List<Long> medicationIds);

    List<Medication> getDroneMedications(Long droneId);
}
