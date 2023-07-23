package com.musala.drones.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.musala.drones.dtos.RegisterDroneDto;
import com.musala.drones.enums.DroneModelEnum;
import com.musala.drones.enums.DroneStateEnum;
import com.musala.drones.models.Drone;
import com.musala.drones.services.DroneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DroneControllerTest {

    @Mock
    private DroneService droneService;
    @InjectMocks
    private DroneController droneController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(droneController).build();
    }

    @Test
    public void testRegister_success() throws Exception {
        Drone returnedDrone = new Drone();
        returnedDrone.setId(1L);
        returnedDrone.setSerialNum("DRONE_1");
        returnedDrone.setModel(DroneModelEnum.Lightweight);
        returnedDrone.setWeightLimit(500);
        returnedDrone.setBatteryCapacity(50);
        returnedDrone.setState(DroneStateEnum.IDLE);
        when(droneService.register(any(RegisterDroneDto.class))).thenReturn(returnedDrone);

        RegisterDroneDto testDto = new RegisterDroneDto();
        testDto.setSerialNum("DRONE_1");
        testDto.setModel(DroneModelEnum.Lightweight);
        testDto.setWeightLimit(500);
        testDto.setBatteryCapacity(50);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer();
        String content = objectWriter.writeValueAsString(testDto);
        String resultStr = objectWriter.writeValueAsString(returnedDrone);
        mockMvc.perform(post("/drone/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> assertEquals(resultStr, mvcResult.getResponse().getContentAsString()));
    }

    @Test
    public void testRegister_badRequestWhenInvalidInput() throws Exception {
        Drone returnedDrone = new Drone();
        returnedDrone.setId(1L);
        returnedDrone.setSerialNum("DRONE_1");
        returnedDrone.setModel(DroneModelEnum.Lightweight);
        returnedDrone.setWeightLimit(500);
        returnedDrone.setBatteryCapacity(50);
        returnedDrone.setState(DroneStateEnum.IDLE);

        RegisterDroneDto testDto = new RegisterDroneDto();
        testDto.setSerialNum("DRONE_1");
        testDto.setModel(DroneModelEnum.Lightweight);
        testDto.setWeightLimit(501);
        testDto.setBatteryCapacity(50);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer();
        String content = objectWriter.writeValueAsString(testDto);
        mockMvc.perform(post("/drone/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}