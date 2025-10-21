package com.pluralsight;

public class Vehicle {
    public int vin;
    public int year;
    public String make;
    public String model;
    public String vehicleType;
    public String color;
    public int odometer;
    public double price;

    public Vehicle(int vin, int year, String make, String model, String vehicleType, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }
}
