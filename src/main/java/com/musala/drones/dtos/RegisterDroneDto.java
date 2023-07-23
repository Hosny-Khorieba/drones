package com.musala.drones.dtos;

import com.musala.drones.enums.DroneModelEnum;
import com.musala.drones.enums.DroneStateEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RegisterDroneDto implements Serializable {
    private static final long serialVersionUID = 4784374301293253066L;

    @Size(max = 100, message = "Serial Number must be less than or equal to 100 characters")
    @NotBlank(message = "Serial Number cannot be empty")
    private String serialNum;
    @NotNull(message = "Drone must have a model")
    private DroneModelEnum model;
    @Max(value = 500, message = "A drone max weight limit is 500 grams")
    private Integer weightLimit;
    @Max(value = 100, message = "Battery percentage cannot exceed 100%")
    @Min(value = 0, message = "Battery percentage cannot be less than 0%")
    private Integer batteryCapacity;
    private DroneStateEnum state;
}
