package com.musala.drones.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.drones.dtos.RegisterMedicationDto;
import com.musala.drones.models.Medication;
import com.musala.drones.services.MedicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MedicationControllerTest {

    @Mock
    private MedicationService medicationService;
    @InjectMocks
    private MedicationController medicationController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(medicationController).build();
    }

    @Test
    public void testRegister_success() throws Exception {
        Medication returnedMedication = new Medication();
        returnedMedication.setId(1L);
        returnedMedication.setCode("MED_1");
        returnedMedication.setName("Med1");
        returnedMedication.setWeight(20);
        returnedMedication.setImageName("Med1Image");

        when(medicationService.register(any(MultipartFile.class), any(RegisterMedicationDto.class))).thenReturn(returnedMedication);

        RegisterMedicationDto testDto = new RegisterMedicationDto();
        testDto.setCode("MED_1");
        testDto.setName("Med1");
        testDto.setWeight(20);
        testDto.setImageName("Med1Image");

        byte[] imageContent = new byte[1024];
        String imageName = "medication_image.jpg";
        MockMultipartFile imageFile = new MockMultipartFile("image", imageName, MediaType.IMAGE_JPEG_VALUE, imageContent);

        ObjectMapper objectMapper = new ObjectMapper();
        MockMultipartFile medicationDtoFile = new MockMultipartFile("medicationDto", null, MediaType.APPLICATION_JSON_VALUE, objectMapper.writeValueAsBytes(testDto));

        mockMvc.perform(multipart("/medication/register")
                        .file(imageFile)
                        .file(medicationDtoFile))
                .andExpect(status().isOk());
    }

}