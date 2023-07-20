package com.musala.drones.repositories;

import com.musala.drones.enums.DroneStateEnum;
import com.musala.drones.models.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    List<Drone> findByStateAndBatteryCapacityGreaterThanEqual(DroneStateEnum state, Integer batteryCapacity);

    @Query(value = "select batteryCapacity from Drone where id = :droneId")
    Integer getBatteryPercentageForDrone(@Param("droneId") Long droneId);
}
