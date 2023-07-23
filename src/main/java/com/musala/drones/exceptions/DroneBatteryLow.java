package com.musala.drones.exceptions;

public class DroneBatteryLow extends RuntimeException{
    private static final long serialVersionUID = 7565401452581517600L;

    public DroneBatteryLow(String msg){
        super(msg);
    }
}
