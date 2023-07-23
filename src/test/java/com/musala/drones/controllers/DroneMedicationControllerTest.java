package com.musala.drones.controllers;

import com.musala.drones.models.Medication;
import com.musala.drones.services.DroneMedicationService;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;

@ExtendWith(MockitoExtension.class)
class DroneMedicationControllerTest {

    @Mock
    private DroneMedicationService droneMedicationService;
    @InjectMocks
    private DroneMedicationController droneMedicationController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(droneMedicationController).build();
    }

    @Test
    void loadDroneWithMedications_success() throws Exception{
        mockMvc.perform(put("/drone/load")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("droneId", String.valueOf(1))
                        .queryParam("medicationIds", "1, 2"))
                .andExpect(status().isOk());
    }

    @Test
    void loadDroneWithMedications_failWhenNoDronePassed() throws Exception{
        mockMvc.perform(put("/drone/load")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("medicationIds", "1, 2"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetDroneMedications_success() throws Exception {
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

        when(droneMedicationService.getDroneMedications(any(Long.class))).thenReturn(returnedMedications);

        mockMvc.perform(get("/medications/{droneId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }
}