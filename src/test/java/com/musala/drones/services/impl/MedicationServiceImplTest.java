package com.musala.drones.services.impl;

import com.musala.drones.dtos.RegisterMedicationDto;
import com.musala.drones.models.Medication;
import com.musala.drones.repositories.MedicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MedicationServiceImplTest {

    @Mock
    private MedicationRepository medicationRepositoryMock;

    @InjectMocks
    private MedicationServiceImpl medicationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister() throws IOException {
        RegisterMedicationDto medicationDto = new RegisterMedicationDto();
        medicationDto.setName("Medication 1");
        medicationDto.setCode("MED_1");
        medicationDto.setWeight(20);
        medicationDto.setImageName("medication_image.jpg");

        String originalFileName = "medication_image.jpg";
        byte[] imageContent = new byte[1024];
        MultipartFile imageFile = new MockMultipartFile(originalFileName, originalFileName, "image/jpeg", imageContent);

        Medication expectedMedication = new Medication();
        expectedMedication.setName("Medication 1");
        expectedMedication.setCode("MED_1");
        expectedMedication.setWeight(20);
        expectedMedication.setImageName("medication_image.jpg");
        expectedMedication.setImagePath("src\\main\\resources\\images\\uploaded_images\\medication_image.jpg_385bedcc-2351-4c2e-8e4b-58d81a055223");

        when(medicationRepositoryMock.save(any(Medication.class))).thenReturn(expectedMedication);

        Medication resultMedication = medicationService.register(imageFile, medicationDto);
        resultMedication.setImagePath("src\\main\\resources\\images\\uploaded_images\\medication_image.jpg_385bedcc-2351-4c2e-8e4b-58d81a055223"); //UUID workaround

        assertEquals(expectedMedication, resultMedication);
    }
}
