package com.musala.drones.repositories;

import com.musala.drones.models.Drone;
import com.musala.drones.models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    List<Medication> findByDrone(Drone drone);
}
