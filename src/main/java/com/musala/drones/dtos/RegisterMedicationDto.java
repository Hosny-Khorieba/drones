package com.musala.drones.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RegisterMedicationDto implements Serializable {
    private static final long serialVersionUID = 8530670522503107655L;

    @Pattern(regexp = "^[a-zA-Z0-9_-]*$", message = "Name must consist of letters and numbers with and _ and - only")
    @NotBlank(message = "Medication Name cannot be blank")
    private String name;
    @Min(value = 1, message = "Medication weight cannot be less than 1 gram")
    private Integer weight;
    @Pattern(regexp = "^[A-Z0-9_]*$", message = "Code must consist of uppercase letters and numbers with and _ only")
    private String code;
    @Pattern(regexp = "^[a-zA-Z0-9._ -]+$", message = "Image name must consist of letters, numbers, ., _, - only")
    private String imageName;
}
