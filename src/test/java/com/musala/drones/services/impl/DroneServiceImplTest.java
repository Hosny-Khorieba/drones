package com.musala.drones.services.impl;

import com.musala.drones.dtos.RegisterDroneDto;
import com.musala.drones.enums.DroneModelEnum;
import com.musala.drones.enums.DroneStateEnum;
import com.musala.drones.models.Drone;
import com.musala.drones.repositories.DroneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DroneServiceImplTest {

    @Mock
    private DroneRepository droneRepositoryMock;

    @InjectMocks
    private DroneServiceImpl droneService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister_success() {
        RegisterDroneDto droneDto = new RegisterDroneDto();
        droneDto.setSerialNum("DRONE_1");
        droneDto.setBatteryCapacity(50);

        Drone expectedDrone = new Drone();
        expectedDrone.setId(1L);
        expectedDrone.setSerialNum("DRONE_1");
        expectedDrone.setState(DroneStateEnum.IDLE);
        expectedDrone.setBatteryCapacity(50);

        when(droneRepositoryMock.save(any(Drone.class))).thenReturn(expectedDrone);

        Drone resultDrone = droneService.register(droneDto);

        assertEquals(expectedDrone, resultDrone);
    }

    @Test
    public void testGetAvailableForLoading_success() {

        List<Drone> returnedList = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            Drone returnedDrone = new Drone();
            returnedDrone.setId((long) i + 1);
            returnedDrone.setSerialNum("DRONE_" + i);
            returnedDrone.setModel(DroneModelEnum.Lightweight);
            returnedDrone.setWeightLimit(500);
            returnedDrone.setBatteryCapacity(50);
            returnedDrone.setState(DroneStateEnum.IDLE);
            returnedList.add(returnedDrone);
        }

        when(droneRepositoryMock.findByStateAndBatteryCapacityGreaterThanEqual(DroneStateEnum.IDLE, 25)).thenReturn(returnedList);

        List<Drone> resultDrones = droneService.getAvailableForLoading();

        assertEquals(returnedList, resultDrones);
    }

    @Test
    public void testGetBatteryPercentage_success() {
        Long droneId = 1L;
        Integer expectedBatteryPercentage = 80;

        when(droneRepositoryMock.getBatteryPercentageForDrone(droneId)).thenReturn(expectedBatteryPercentage);

        Integer resultBatteryPercentage = droneService.getBatteryPercentage(droneId);

        assertEquals(expectedBatteryPercentage, resultBatteryPercentage);
    }

}