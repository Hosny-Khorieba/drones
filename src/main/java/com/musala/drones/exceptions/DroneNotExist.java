package com.musala.drones.exceptions;

public class DroneNotExist extends RuntimeException{
    private static final long serialVersionUID = 2085884866354609728L;
    public DroneNotExist(String msg){
        super(msg);
    }
}
