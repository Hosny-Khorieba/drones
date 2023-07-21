package com.musala.drones.services.impl;

import com.musala.drones.dtos.RegisterMedicationDto;
import com.musala.drones.mappers.MedicationMapper;
import com.musala.drones.models.Medication;
import com.musala.drones.repositories.MedicationRepository;
import com.musala.drones.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public Medication register(MultipartFile image, RegisterMedicationDto medicationDto) {

        Medication medication = MedicationMapper.INSTANCE.RegisterMedicationDtoToMedication(medicationDto);

        try {
            StringBuilder fileNameBuilder =
                    new StringBuilder(medicationDto.getImageName() != null ? medicationDto.getImageName() : image.getOriginalFilename())
                            .append("_")
                            .append(UUID.randomUUID());

            String fileName = fileNameBuilder.toString();
            String resourcePath = new ClassPathResource("src/main/resources/images/").getPath();

            String uploadedImagesPath = resourcePath + "uploaded_images/";
            Files.createDirectories(Paths.get(uploadedImagesPath));

            Path filePath = Paths.get(uploadedImagesPath, fileName);

            Files.write(filePath, image.getBytes());

            medication.setImagePath(filePath.toString());
            medication.setImageName(medication.getImageName() != null ? medication.getImageName() : image.getOriginalFilename());

            medicationRepository.save(medication);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return medication;
    }
}
