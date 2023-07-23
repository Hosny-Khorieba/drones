package com.musala.drones.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorDto implements Serializable {
    private static final long serialVersionUID = -7668151874595065034L;

    int code;
    Date timestamp;
    String message;
}
