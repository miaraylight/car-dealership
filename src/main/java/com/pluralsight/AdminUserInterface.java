package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.pluralsight.ContractDataManager.getContractData;
import static com.pluralsight.DealershipFileManager.getDealership;
import static com.pluralsight.DealershipFileManager.saveDealership;

public class AdminUserInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private ContractData contractData;
    private Dealership dealership;

    private void init () {
        this.contractData = getContractData();
        this.dealership = getDealership();
    }

    private void displayContracts(List<Contract> contracts) {
        if (contracts == null || contracts.isEmpty()) {
            System.out.println("No contracts to display.");
            return;
        }

        String header = String.format(
                "%-6s %-10s %-15s %-20s %-10s %-10s %-8s %-10s %-10s %-10s %-10s %-10s",
                "TYPE", "DATE", "CUSTOMER", "EMAIL", "VIN", "MAKE", "MODEL",
                "PRICE", "TOTAL", "FINANCE", "MONTHLY", "TERM");
        System.out.println(header);
        System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");

        for (Contract contract : contracts) {
            Vehicle v = contract.getVehicleSold();
            String type;
            String finance = "";
            double monthly = 0.0;
            String term = "";

            if (contract instanceof SalesContract sc) {
                type = "SALE";
                finance = sc.isFinance() ? "YES" : "NO";
                monthly = sc.getMonthlyPayment();
                term = sc.isFinance()
                        ? (v.getPrice() >= 10000 ? "48 mo" : "24 mo")
                        : "-";
            } else if (contract instanceof LeaseContract lc) {
                type = "LEASE";
                finance = "YES"; // Leases are always financed
                monthly = lc.getMonthlyPayment();
                term = "36 mo";
            } else {
                type = "OTHER";
            }

            String result = String.format(
                    "%-6s %-10s %-15s %-20s %-10d %-10s %-8s $%,10.2f $%,10.2f %-10s $%,10.2f %-10s",
                    type,
                    contract.getDate(),
                    contract.getCustomerName(),
                    contract.getCustomerEmail(),
                    v.getVin(),
                    v.getMake(),
                    v.getModel(),
                    v.getPrice(),
                    contract.getTotalPrice(),
                    finance,
                    monthly,
                    term
            );
            System.out.println(result);
        }
    }


    public void display() {
        init();
        int choice;

        do {
            displayMainAdminMenu();
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) { // while input is not a number
                System.out.print("Invalid input. Enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    processGetAllContracts();
                    break;
                case 2:
                    processGetLeaseContracts();
                    break;
                case 3:
                    processGetSalesContracts();
                    break;
                case 4:
                    processGetLast(10);
                    break;
                case 5:
                    processAddVehicleRequest();
                    break;
                case 6:
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

    private void displayMainAdminMenu() {
        System.out.println("\n==============================");
        System.out.println("     DEALERSHIP MAIN ADMIN MENU     ");
        System.out.println("==============================");
        System.out.println("[1] List all contracts");
        System.out.println("[2] List all lease");
        System.out.println("[3] List all sales");
        System.out.println("[4] List last 10");
        System.out.println("[5] Add a vehicle");
        System.out.println("[6] Remove a vehicle");
        System.out.println("[0] Exit");
        System.out.println("==============================");
    }

    public void processGetAllContracts() {
        List<Contract> contracts = contractData.getAllContracts();
        displayContracts(contracts);
    }

    public void processGetLeaseContracts() {
        List<Contract> leaseContracts = contractData.getLeaseContracts();
        displayContracts(leaseContracts);
    }

    public void processGetSalesContracts() {
        List<Contract> salesContracts = contractData.getSaleContracts();
        displayContracts(salesContracts);
    }

    public void processGetLast(int number) {
        List<Contract> lastContracts = contractData.getLastByNumber(number);
        displayContracts(lastContracts);
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
        displayVehicle(vehicle);

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
            displayVehicle(vehicleToRemove);
            System.out.println("\n✅ Vehicle with VIN " + vin + " was successfully removed.");
            saveDealership(dealership);
        } else {
            System.out.println("\n⚠️ No vehicle found with VIN " + vin + ".");
        }
    }

    private void displayVehicle(Vehicle vehicle) {
        String header = String.format("%-8s %-6s %-10s %-12s %-8s %-10s %-10s %-11s",
                "VIN", "YEAR", "MAKE", "MODEL", "TYPE", "COLOR", "ODOMETER", "PRICE");
        System.out.println(header);
        System.out.println("──────────────────────────────────────────────────────────────────────────────────");


            String result = String.format("%-8d %-6d %-10s %-12s %-8s %-10s %-10d $%,10.2f",
                    vehicle.getVin(), vehicle.getYear(),
                    vehicle.getMake(), vehicle.getModel(),
                    vehicle.getVehicleType(), vehicle.getColor(),
                    vehicle.getOdometer(), vehicle.getPrice());
            System.out.println(result);
    }

}
