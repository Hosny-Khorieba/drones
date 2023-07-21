package com.musala.drones.services;

import com.musala.drones.dtos.RegisterMedicationDto;
import com.musala.drones.models.Medication;
import org.springframework.web.multipart.MultipartFile;

public interface MedicationService {
    Medication register(MultipartFile image, RegisterMedicationDto medicationDto);
}
