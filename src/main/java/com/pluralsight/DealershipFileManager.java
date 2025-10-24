package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DealershipFileManager {

    static public Dealership getDealership() {
        Dealership dealership = new Dealership();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("inventory.csv"))){
            //dealership
            String line = bufferedReader.readLine();
            String[] dealershipData = line.split("\\|");

            //inventory
            ArrayList<Vehicle> inventory = new ArrayList<>();

            while((line = bufferedReader.readLine()) != null) {
                String[] vehicleData = line.split("\\|");

                int vin = Integer.parseInt(vehicleData[0]);
                int year = Integer.parseInt(vehicleData[1]);
                String make = vehicleData[2];
                String model = vehicleData[3];
                String vehicleType = vehicleData[4];
                String color = vehicleData[5];
                int odometer = Integer.parseInt(vehicleData[6]);
                double price = Double.parseDouble(vehicleData[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

                inventory.add(vehicle);
            }

            dealership = new Dealership(dealershipData[0], dealershipData[1], dealershipData[2], inventory);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return dealership;
    }

    public static void saveDealership(Dealership dealership) {
        String dealershipData = dealership.toCsv();
        String inventoryData = dealership.getInventoryInCsv();
        try {
            FileWriter writer = new FileWriter("inventory.csv");
            writer.write(dealershipData + inventoryData);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
