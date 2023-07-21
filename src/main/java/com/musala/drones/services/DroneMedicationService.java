package com.musala.drones.services;

import java.util.List;

public interface DroneMedicationService {
    void loadDroneWithMedications(Long droneId, List<Long> medicationIds);
}
