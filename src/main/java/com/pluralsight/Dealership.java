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

    public List<Vehicle> getVehiclesByPrice (double minPrice, double maxPrice) {
        ArrayList<Vehicle> filteredByPrice = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getPrice() >= minPrice && v.getPrice() <= maxPrice) {
                filteredByPrice.add(v);
            }
        }
        return  filteredByPrice;
    }

    public List<Vehicle> getVehiclesByMakeModel (String make, String model) {
        ArrayList<Vehicle> filteredByMake = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getMake().toLowerCase().equals(make) && v.getModel().toLowerCase().equals(model)) {
                filteredByMake.add(v);
            }
        }
        return filteredByMake;

    }

    public List<Vehicle> getVehiclesByYear (int minYear, int maxYear) {
        ArrayList<Vehicle> filteredByYear = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getYear() >= minYear && v.getYear() <= maxYear) {
                filteredByYear.add(v);
            }
        }
        return filteredByYear;
    }

    public List<Vehicle> getVehiclesByColor (String color) {
        ArrayList<Vehicle> filteredByColor = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getColor().toLowerCase().equals(color)) {
                filteredByColor.add(v);
            }
        }
        return filteredByColor;
    }

    public List<Vehicle> getVehiclesByMileage (int minMiles, int maxMiles) {
        ArrayList<Vehicle> filteredByMileage = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getOdometer() >= minMiles && v.getOdometer() <= maxMiles) {
                filteredByMileage.add(v);
            }
        }

        return filteredByMileage;
    }

    public List<Vehicle> getVehiclesByType (String vehicleType) {
        ArrayList<Vehicle> filteredByType = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getVehicleType().toLowerCase().equals(vehicleType)) {
                filteredByType.add(v);
            }
        }
        return filteredByType;
    }

    public List<Vehicle> getAllVehicles () {
        return inventory;
    }

    public Vehicle findVehicleByVin(int vin) {
        Vehicle result = null;
        for (Vehicle v: inventory) {
            if (v.getVin() == vin) {
                result = v;
            }
        }
        return result;
    }

    public void addVehicle (Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle (Vehicle vehicle) {
        inventory.remove(vehicle);
    }

    public String toCsv () {
        return String.format("%s|%s|%s", name, address, phone);
    }

    public String getInventoryInCsv () {
        StringBuilder inventoryInCsv = new StringBuilder();
        for (Vehicle vehicle: inventory) {
            String vehicleData = String.format("\n%d|%d|%s|%s|%s|%s|%d|%f",
                    vehicle.getVin(), vehicle.getYear(),
                    vehicle.getMake(), vehicle.getModel(),
                    vehicle.getVehicleType(), vehicle.getColor(),
                    vehicle.getOdometer(), vehicle.getPrice());
            inventoryInCsv.append(vehicleData);
        }

        return inventoryInCsv.toString();
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
