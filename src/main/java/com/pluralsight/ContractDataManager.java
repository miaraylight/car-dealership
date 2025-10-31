package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ContractDataManager {

    static public ContractData getContractData() {
        ArrayList<Contract> contracts = new ArrayList<>();
        ContractData contractData = new ContractData(contracts);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("contracts.csv"))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) continue;

                String[] data = line.split("\\|");

                String contractType = data[0];
                String date = data[1];
                String customerName = data[2];
                String customerEmail = data[3];

                // Create Vehicle object from fields
                int vin = Integer.parseInt(data[4]);
                int year = Integer.parseInt(data[5]);
                String make = data[6];
                String model = data[7];
                String vehicleType = data[8];
                String color = data[9];
                int odometer = Integer.parseInt(data[10]);
                double price = Double.parseDouble(data[11]);

                Vehicle vehicleSold = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

                if (contractType.equalsIgnoreCase("SALE")) {
                    double salesTaxAmount = Double.parseDouble(data[12]);
                    double recordingFee = Double.parseDouble(data[13]);
                    double processingFee = Double.parseDouble(data[14]);
                    double totalPrice = Double.parseDouble(data[15]);
                    boolean isFinance = data[16].equalsIgnoreCase("YES");
                    double monthlyPayment = Double.parseDouble(data[17]);

                    SalesContract salesContract = new SalesContract(
                            date, customerName, customerEmail, vehicleSold,
                            salesTaxAmount, recordingFee, processingFee, totalPrice,
                            isFinance, monthlyPayment
                    );

                    contracts.add(salesContract);

                } else if (contractType.equalsIgnoreCase("LEASE")) {
                    double expectedEndingValue = Double.parseDouble(data[12]);
                    double leaseFee = Double.parseDouble(data[13]);
                    double totalPrice = Double.parseDouble(data[14]);
                    double monthlyPayment = Double.parseDouble(data[15]);

                    LeaseContract leaseContract = new LeaseContract(
                            date, customerName, customerEmail, vehicleSold,
                            expectedEndingValue, leaseFee, totalPrice, monthlyPayment
                    );

                    contracts.add(leaseContract);
                }
            }

        } catch (IOException e) {
            System.out.println("⚠️ Error reading contracts.csv: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("⚠️ Error parsing contract data: " + e.getMessage());
        }

        return contractData;
    }


//    static public ContractData getContractData() {
//        ArrayList<Contract> contracts = new ArrayList<>();
//        ContractData contractData = new ContractData(contracts);
//
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("contracts.csv"))){
//
//            String line;
//
//            while((line = bufferedReader.readLine()) != null) {
//                String[] contract = line.split("\\|");
//
//                if (contract[0].equals("SALE")) {
//
//                    SalesContract salesContract = new SalesContract();
//
//                    contracts.add(salesContract);
//                } else if (contract[0].equals("LEASE")) {
//                    LeaseContract leaseContract = new LeaseContract();
//
//                    contracts.add(leaseContract);
//                }
//            }
//
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//
//        return contractData;
//    }


    public static void saveContractData(ContractData contractData){
        String contracts = contractData.getContractsInCsv();
        try {
            FileWriter writer = new FileWriter("contracts.csv");
            writer.write(contracts);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
