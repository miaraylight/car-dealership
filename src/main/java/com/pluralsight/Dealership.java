package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name ;
    private String address;
    private String phone;

    private ArrayList<Vehicle> inventory = new ArrayList<>();

    public Dealership() {
        this.name = "";
        this.address = "";
        this.phone = "";
        this.inventory = new ArrayList<>();
    }

    public Dealership(String name, String address, String phone, ArrayList<Vehicle> inventory) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = inventory;
    }

//    public List<Vehicle> getVehiclesByPrice (double min, double max) {
//
//    }
//
//    public List<Vehicle> getVehiclesByMakeModel (String make, String model) {
//
//    }
//
//    public List<Vehicle> getVehiclesByYear (int minYear, int maxYear) {
//
//    }
//
//    public List<Vehicle> getVehiclesByColor (String color) {
//
//    }
//
//    public List<Vehicle> getVehiclesByMileage (int minMiles, int maxMiles) {
//
//    }
//
//    public List<Vehicle> getVehiclesByType (String vehicleType) {
//
//    }
//
    public List<Vehicle> getAllVehicles () {
        return inventory;
    }

    public void addVehicle (Vehicle vehicle) {

    }

    public void removeVehicle (Vehicle vehicle) {

    }

    @Override
    public String toString() {
        return "Dealership{" +
                "phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
