package com.musala.drones.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Data
public class Medication implements Serializable {
    private static final long serialVersionUID = 2265961534781776440L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^[a-zA-Z0-9_-]*$", message = "Name must consist of letters and numbers with and _ and - only")
    @NotBlank(message = "Medication Name cannot be blank")
    private String name;
    @Min(value = 1, message = "Medication weight cannot be less than 1 gram")
    private Integer weight;
    @Pattern(regexp = "^[A-Z0-9_]*$", message = "Code must consist of uppercase letters and numbers with and _ only")
    private String code;
    private String imageName;
    private String imagePath;
    @ManyToOne
    private Drone drone;
}
