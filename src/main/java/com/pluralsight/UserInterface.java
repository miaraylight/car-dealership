package com.pluralsight;

import java.util.List;
import java.util.Scanner;

import static com.pluralsight.DealershipFileManager.getDealership;
import static com.pluralsight.DealershipFileManager.saveDealership;
import static com.pluralsight.ContractDataManager.getContractData;
import static com.pluralsight.ContractDataManager.saveContractData;

public class UserInterface {
    private Dealership dealership;
    private ContractData contractData;
    private static final Scanner scanner = new Scanner(System.in);

    private void init () {
        this.dealership = getDealership();
        this.contractData = getContractData();
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
                    processSellVehicleRequest();
                    break;
                case 9:
                    processLeaseVehicleRequest();
                    break;
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);
    }

    public void processGetByPriceRequest() {
        System.out.println("Enter min price: ");
        double minPrice = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter max price: ");
        double maxPrice = scanner.nextDouble();
        scanner.nextLine();

        List<Vehicle> filteredByPrice =dealership.getVehiclesByPrice(minPrice, maxPrice);

        if (!filteredByPrice.isEmpty()) {
            displayVehicles(filteredByPrice);
            System.out.println(filteredByPrice.size() + " vehicle in range " + minPrice + "-" + maxPrice + " found: ");
        } else {
            System.out.println("\n⚠️ No vehicle found in range" + + minPrice + "-" + maxPrice + ".");
        }
    }

    public void processGetByMakeModelRequest() {
        System.out.println("Enter make");
        String make = scanner.nextLine().trim().toLowerCase();

        System.out.println("Enter model");
        String model = scanner.nextLine().trim().toLowerCase();

        List<Vehicle> filteredByType = dealership.getVehiclesByMakeModel(make, model);

        if (!filteredByType.isEmpty()) {
            displayVehicles(filteredByType);
            System.out.println(filteredByType.size() + " vehicle by make&model "  + make + " " + model + " found: ");
        } else {
            System.out.println("\n⚠️ No vehicle found with make&model " + make + " " + model + ".");
        }
    }

    public void processGetByYearRequest() {
        System.out.println("Enter min year of the vehicle");
        int minYear = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter max year of the vehicle");
        int maxYear = scanner.nextInt();
        scanner.nextLine();


        List<Vehicle> filteredByYear = dealership.getVehiclesByYear(minYear, maxYear);

        if (!filteredByYear.isEmpty()) {
            displayVehicles(filteredByYear);
            System.out.println(filteredByYear.size() + " vehicle in range " + minYear + "-" + maxYear +  " found: ");
        } else {
            System.out.println("\n⚠️ No vehicle found in range " + minYear + "-" + maxYear +  ".");
        }
    }

    public void processGetByColorRequest() {
        System.out.println("Enter color of the vehicle");
        String color = scanner.nextLine().trim().toLowerCase();

        List<Vehicle> filteredByColor = dealership.getVehiclesByColor(color);

        if (!filteredByColor.isEmpty()) {
            displayVehicles(filteredByColor);
            System.out.println(filteredByColor.size() + " vehicle color of  " + color + " found: ");
        } else {
            System.out.println("\n⚠️ No vehicle found with color " + color + ".");
        }
    }

    public void processGetByMileageRequest() {
        System.out.println("Enter min odometer: ");
        int minMiles = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter max odometer: ");
        int maxMiles = scanner.nextInt();
        scanner.nextLine();

        List<Vehicle> filteredByMileage = dealership.getVehiclesByMileage(minMiles, maxMiles);

        if (!filteredByMileage.isEmpty()) {
            displayVehicles(filteredByMileage);
            System.out.println(filteredByMileage.size() + " vehicle in range " + minMiles + "-" + maxMiles + " found: ");
        } else {
            System.out.println("\n⚠️ No vehicle found in range" + + minMiles + "-" + maxMiles + ".");
        }
    }

    public void processGetByVehicleTypeRequest() {
        System.out.println("Enter type of the vehicle");
        String type = scanner.nextLine().trim().toLowerCase();

        List<Vehicle> filteredByType = dealership.getVehiclesByType(type);

        if (!filteredByType.isEmpty()) {
            displayVehicles(filteredByType);
            System.out.println(filteredByType.size() + " vehicle type of  " + type + " found: ");
        } else {
            System.out.println("\n⚠️ No vehicle found with type " + type + ".");
        }

    }

    public void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    public void processSellVehicleRequest(){
        System.out.println("\n--- Sell Vehicle ---");

        System.out.print("Enter sale date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter customer email: ");
        String customerEmail = scanner.nextLine();

        System.out.print("Enter VIN of vehicle being sold: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicleSold = dealership.findVehicleByVin(vin);
        if (vehicleSold == null) {
            System.out.println("❌ Vehicle not found. Sale cancelled.");
            return;
        }

        System.out.print("Is the customer financing the purchase? (yes/no): ");
        String financeInput = scanner.nextLine().trim().toLowerCase();
        boolean isFinance = financeInput.equals("yes") || financeInput.equals("y");

        System.out.println("\n--- Confirm Sale ---");
        System.out.println("Date: " + date);
        System.out.println("Customer: " + customerName + " (" + customerEmail + ")");
        System.out.println("Vehicle: " + vehicleSold.getYear() + " " + vehicleSold.getMake() + " " + vehicleSold.getModel());
        System.out.println("Financed: " + (isFinance ? "Yes" : "No"));

        System.out.print("\nConfirm sale? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (!confirm.equals("yes")) {
            System.out.println("Sale cancelled.");
            return;
        }

        SalesContract salesContract = new SalesContract(date, customerName, customerEmail, vehicleSold, isFinance);

        contractData.addContract(salesContract); // adds to list of contracts
        saveContractData(contractData); // writes to csv file
        dealership.removeVehicle(vehicleSold); // remove from inventory
        saveDealership(dealership); // update csv file

        System.out.println("\n✅ Vehicle sold successfully to " + customerName + "!");

    };

    public void processLeaseVehicleRequest() {
        System.out.println("\n--- Lease Vehicle ---");

        System.out.print("Enter lease date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter customer email: ");
        String customerEmail = scanner.nextLine();

        System.out.print("Enter VIN of vehicle being leased: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicleLeased = dealership.findVehicleByVin(vin);
        if (vehicleLeased == null) {
            System.out.println("❌ Vehicle not found. Lease cancelled.");
            return;
        }

        System.out.println("\n--- Confirm Lease ---");
        System.out.println("Date: " + date);
        System.out.println("Customer: " + customerName + " (" + customerEmail + ")");
        System.out.println("Vehicle: " + vehicleLeased.getYear() + " " + vehicleLeased.getMake() + " " + vehicleLeased.getModel());
        System.out.println("Leased for 36 month starting: " + date);

        System.out.print("\nConfirm Lease? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (!confirm.equals("yes")) {
            System.out.println("Lease cancelled.");
            return;
        }

        LeaseContract leaseContract = new LeaseContract(date, customerName, customerEmail, vehicleLeased);

        contractData.addContract(leaseContract); // adds to list of contracts
        saveContractData(contractData); // writes to csv file
        dealership.removeVehicle(vehicleLeased); // remove from inventory
        saveDealership(dealership); // update csv file

        System.out.println("\n✅ Vehicle leased successfully to " + customerName + "!");
    };

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
        System.out.println("[8] Sell a vehicle");
        System.out.println("[9] Lease a vehicle");
        System.out.println("[0] Exit");
        System.out.println("==============================");
    }

}
