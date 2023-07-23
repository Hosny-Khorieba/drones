package com.musala.drones.services.impl;

import com.musala.drones.enums.DroneStateEnum;
import com.musala.drones.exceptions.DroneNotExist;
import com.musala.drones.exceptions.DroneWeightLimitExceeded;
import com.musala.drones.exceptions.MedicationNotExist;
import com.musala.drones.models.Drone;
import com.musala.drones.models.Medication;
import com.musala.drones.repositories.DroneRepository;
import com.musala.drones.repositories.MedicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DroneMedicationServiceImplTest {

    @Mock
    private DroneRepository droneRepositoryMock;

    @Mock
    private MedicationRepository medicationRepositoryMock;

    @InjectMocks
    private DroneMedicationServiceImpl droneMedicationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadDroneWithMedications_Success() {
        Long droneId = 1L;
        List<Long> medicationIds = Arrays.asList(101L, 102L, 103L);

        Drone drone = new Drone();
        drone.setId(droneId);
        drone.setState(DroneStateEnum.IDLE);
        drone.setWeightLimit(1000);

        List<Medication> medications = new ArrayList<>();
        for (Long medicationId : medicationIds) {
            Medication medication = new Medication();
            medication.setId(medicationId);
            medication.setWeight(200);
            medications.add(medication);
        }

        when(droneRepositoryMock.findById(droneId)).thenReturn(Optional.of(drone));
        for (Long medicationId : medicationIds) {
            when(medicationRepositoryMock.findById(medicationId)).thenReturn(Optional.of(medications.get(medicationIds.indexOf(medicationId))));
        }
        droneMedicationService.loadDroneWithMedications(droneId, medicationIds);
    }

    @Test
    public void testLoadDroneWithMedications_DroneNotExist() {
        Long droneId = 1L;
        List<Long> medicationIds = Arrays.asList(101L, 102L, 103L);

        when(droneRepositoryMock.findById(droneId)).thenReturn(Optional.empty());

        assertThrows(DroneNotExist.class, () -> droneMedicationService.loadDroneWithMedications(droneId, medicationIds));
    }

    @Test
    public void testLoadDroneWithMedications_MedicationNotExist() {
        Long droneId = 1L;
        List<Long> medicationIds = Arrays.asList(101L, 102L, 103L);

        Drone drone = new Drone();
        drone.setId(droneId);
        drone.setState(DroneStateEnum.IDLE);
        drone.setWeightLimit(1000);

        when(droneRepositoryMock.findById(droneId)).thenReturn(Optional.of(drone));
        when(medicationRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(MedicationNotExist.class, () -> droneMedicationService.loadDroneWithMedications(droneId, medicationIds));
    }

    @Test
    public void testLoadDroneWithMedications_DroneWeightLimitExceeded() {
        Long droneId = 1L;
        List<Long> medicationIds = Arrays.asList(1L, 2L, 3L);

        Drone drone = new Drone();
        drone.setId(droneId);
        drone.setState(DroneStateEnum.IDLE);
        drone.setWeightLimit(400);

        List<Medication> medications = new ArrayList<>();
        for (Long medicationId : medicationIds) {
            Medication medication = new Medication();
            medication.setId(medicationId);
            medication.setWeight(200);
            medications.add(medication);
        }

        when(droneRepositoryMock.findById(droneId)).thenReturn(Optional.of(drone));
        for (Long medicationId : medicationIds) {
            when(medicationRepositoryMock.findById(medicationId)).thenReturn(Optional.of(medications.get(medicationIds.indexOf(medicationId))));
        }

        assertThrows(DroneWeightLimitExceeded.class, () -> droneMedicationService.loadDroneWithMedications(droneId, medicationIds));
    }

    @Test
    public void testGetDroneMedications() {
        Long droneId = 1L;

        Drone drone = new Drone();
        drone.setId(droneId);

        List<Medication> returnedMedications = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            Medication returnedMedication = new Medication();
            returnedMedication.setId((long) i + 1);
            returnedMedication.setCode("MED_" + i);
            returnedMedication.setName("Med" + i);
            returnedMedication.setWeight(20);
            returnedMedication.setImageName("Med" + i + "Image");
            returnedMedications.add(returnedMedication);
        }

        when(droneRepositoryMock.getById(droneId)).thenReturn(drone);
        when(medicationRepositoryMock.findByDrone(drone)).thenReturn(returnedMedications);

        List<Medication> resultMedications = droneMedicationService.getDroneMedications(droneId);

        assertEquals(returnedMedications, resultMedications);
    }
}
