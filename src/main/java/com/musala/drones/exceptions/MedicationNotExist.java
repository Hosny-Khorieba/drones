package com.musala.drones.exceptions;

public class MedicationNotExist extends RuntimeException{
    private static final long serialVersionUID = -6593325385440014364L;

    public MedicationNotExist(String msg){
        super(msg);
    }
}
