package com.musala.drones.models;

import com.musala.drones.enums.DroneModelEnum;
import com.musala.drones.enums.DroneStateEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Drone implements Serializable {
    private static final long serialVersionUID = -4243884016164211232L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 100, message = "Serial Number must be less than or equal to 100 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Serial Number must consist of letters only")
    @NotBlank(message = "Serial Number cannot be empty")
    private String serialNum;
    @Enumerated(EnumType.STRING)
    private DroneModelEnum model;
    @Max(value = 500, message = "A drone max weight limit is 500 grams")
    private Integer weightLimit;
    @Max(value = 100, message = "Battery percentage cannot exceed 100%")
    @Min(value = 0, message = "Battery percentage cannot be less than 0%")
    private Integer batteryCapacity;
    @Enumerated(EnumType.STRING)
    private DroneStateEnum state;
    @OneToMany(mappedBy = "drone")
    private List<Medication> medications;
}
