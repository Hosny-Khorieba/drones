package com.musala.drones.mappers;

import com.musala.drones.dtos.RegisterMedicationDto;
import com.musala.drones.models.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MedicationMapper {
    MedicationMapper INSTANCE = Mappers.getMapper(MedicationMapper.class);

    Medication RegisterMedicationDtoToMedication(RegisterMedicationDto medicationDto);
}
