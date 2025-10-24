package com.pluralsight;

import java.util.List;
import java.util.Scanner;

import static com.pluralsight.DealershipFileManager.getDealership;
import static com.pluralsight.DealershipFileManager.saveDealership;

public class UserInterface {
    private Dealership dealership;
    private static final Scanner scanner = new Scanner(System.in);

    private void init () {
        this.dealership = getDealership();
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        String header = String.format("%-8s %-6s %-10s %-12s %-8s %-10s %-10s %-11s",
                "VIN", "YEAR", "MAKE", "MODEL", "TYPE", "COLOR", "ODOMETER", "PRICE");
        System.out.println(header);
        System.out.println("──────────────────────────────────────────────────────────────────────────────────");

        for (Vehicle vehicle: vehicles) {
            String result = String.format("%-8d %-6d %-10s %-12s %-8s %-10s %-10d $%,10.2f",
                    vehicle.getVin(), vehicle.getYear(),
                    vehicle.getMake(), vehicle.getModel(),
                    vehicle.getVehicleType(), vehicle.getColor(),
                    vehicle.getOdometer(), vehicle.getPrice());
            System.out.println(result);
        }
    }

    public void display() {
        init();
        int choice;

        do {
            displayMainMenu();
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) { // while input is not a number
                System.out.print("Invalid input. Enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    processGetAllVehiclesRequest();
                    break;
                case 2:
                    processGetByPriceRequest();
                    break;
                case 3:
                    processGetByMakeModelRequest();
                    break;
                case 4:
                    processGetByYearRequest();
                    break;
                case 5:
                    processGetByColorRequest();
                    break;
                case 6:
                    processGetByMileageRequest();
                    break;
                case 7:
                    processGetByVehicleTypeRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);
    }

    public void processGetByPriceRequest() {}

    public void processGetByMakeModelRequest() {}

    public void processGetByYearRequest() {}

    public void processGetByColorRequest() {}

    public void processGetByMileageRequest() {}

    public void processGetByVehicleTypeRequest() {}

    public void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    public void processAddVehicleRequest() {
        System.out.print("Enter VIN: ");
        int vin = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter make: ");
        String make = scanner.nextLine();

        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        System.out.print("Enter vehicle type (e.g., SUV, Truck, Sedan): ");
        String vehicleType = scanner.nextLine();

        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        System.out.print("Enter odometer reading: ");
        int odometer = scanner.nextInt();

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        // Create the Vehicle object
        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

        // Add to dealership
        dealership.addVehicle(vehicle);

        System.out.println("\n✅ Vehicle added successfully!");

        saveDealership(dealership);
    }

    public void processRemoveVehicleRequest() {
        System.out.println("Enter vin number of the vehicle");
        int vin = scanner.nextInt();
        scanner.nextLine();

        List<Vehicle> vehicles = dealership.getAllVehicles();
        Vehicle vehicleToRemove = null;
        for (Vehicle v : vehicles) {
            if (v.getVin() == vin) {
                vehicleToRemove = v;
                break;
            }
        }

        if (vehicleToRemove != null) {
            dealership.removeVehicle(vehicleToRemove);
            System.out.println("\n✅ Vehicle with VIN " + vin + " was successfully removed.");
            saveDealership(dealership);
        } else {
            System.out.println("\n⚠️ No vehicle found with VIN " + vin + ".");
        }
    }

    private void displayMainMenu() {
        System.out.println("\n==============================");
        System.out.println("     DEALERSHIP MAIN MENU     ");
        System.out.println("==============================");
        System.out.println("[1] List all vehicles");
        System.out.println("[2] Search by price range");
        System.out.println("[3] Search by make and model");
        System.out.println("[4] Search by year range");
        System.out.println("[5] Search by color");
        System.out.println("[6] Search by mileage range");
        System.out.println("[7] Search by vehicle type");
        System.out.println("[8] Add a vehicle");
        System.out.println("[9] Remove a vehicle");
        System.out.println("[0] Exit");
        System.out.println("==============================");
    }

}
