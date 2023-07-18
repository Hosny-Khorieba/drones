package com.musala.drones.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.musala.drones.enums.DroneModelEnum;
import com.musala.drones.enums.DroneStateEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Drone implements Serializable {
    private static final long serialVersionUID = -4243884016164211232L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 100)
    @NotBlank
    private String serialNum;
    @Enumerated(EnumType.STRING)
    private DroneModelEnum model;
    @Max(value = 500)
    private Integer weightLimit;
    @Max(value = 100)
    @Min(value = 0)
    private Integer batteryCapacity;
    @Enumerated(EnumType.STRING)
    private DroneStateEnum state;
    @OneToMany(mappedBy = "drone")
    @JsonIgnore
    private List<Medication> medications;
}
