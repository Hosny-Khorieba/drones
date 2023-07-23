package com.musala.drones.exceptions;

public class DroneWeightLimitExceeded extends RuntimeException{
    private static final long serialVersionUID = 1758636408775714546L;

    public DroneWeightLimitExceeded(String msg){
        super(msg);
    }
}
