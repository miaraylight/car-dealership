package com.pluralsight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ContractData {
    private ArrayList<Contract> contracts = new ArrayList<>();

    public ContractData(){}

    public ContractData(ArrayList<Contract> contracts) {
        this.contracts = contracts;
    }

    public List<Contract> getAllContracts(){
        return contracts;
    }

    public List<Contract> getLeaseContracts() {
        List<Contract> leaseContracts = new ArrayList<>();
        for (Contract contract: contracts) {
            if (contract instanceof LeaseContract) {
                leaseContracts.add((LeaseContract) contract);
            }
        }

        return leaseContracts;
    }

    public List<Contract> getSaleContracts() {
        List<Contract> saleContracts = new ArrayList<>();
        for (Contract contract: contracts) {
            if (contract instanceof SalesContract) {
                saleContracts.add((SalesContract) contract);
            }
        }

        return saleContracts;
    }

    public List<Contract> getLastByNumber(int number) {
        //sort by date
        //get newest [number] amount of contracts and return it as an array
        List<Contract> lastContracts = new ArrayList<>(contracts); // copy existing list

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        lastContracts.sort((c1, c2) -> {
            LocalDate date1 = LocalDate.parse(c1.getDate(), formatter);
            LocalDate date2 = LocalDate.parse(c2.getDate(), formatter);
            return date2.compareTo(date1);
        });

        // If fewer contracts than requested, just return all
        if (number >= lastContracts.size()) {
            return lastContracts;
        }

        return lastContracts.subList(0, number);
    }

    public void addContract(Contract contract) {
        contracts.add(contract);
    }

    public String getContractsInCsv() {
        StringBuilder contractsCsv = new StringBuilder();
        for (Contract contract: contracts) {
            Vehicle vehicleSold = contract.getVehicleSold();
            if (contract instanceof SalesContract) {
                 String saleContract = String.format("\n%s|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%f|%f|%f|%f|%f|%s|%f",
                       "SALE", contract.getDate(),// CONTRACT_TYPE|DATE|
                       contract.getCustomerName(), contract.getCustomerEmail(),// CUSTOMER_NAME|CUSTOMER_EMAIL|
                        vehicleSold.getVin(), vehicleSold.getYear(),// VIN|YEAR|
                        vehicleSold.getMake(), vehicleSold.getModel(),// MAKE|MODEL|
                        vehicleSold.getVehicleType(), vehicleSold.getColor(),// VEHICLE_TYPE|COLOR|
                        vehicleSold.getOdometer(), vehicleSold.getPrice(),// ODOMETER|VEHICLE_PRICE|
                        ((SalesContract) contract).getSalesTaxAmount(),// SALES_TAX|
                        ((SalesContract) contract).getRecordingFee(),//RECORDING_FEE|
                        ((SalesContract) contract).getProcessingFee(), contract.getTotalPrice(),//PROCESSING_FEE|TOTAL_PRICE|
                        ((SalesContract) contract).isFinance() ? "yes" : "no", contract.getMonthlyPayment()// FINANCE_OPTION|MONTHLY_PAYMENT
                        );
                contractsCsv.append(saleContract);

            } else if (contract instanceof LeaseContract) {
                String leaseContract = String.format("\n%s|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%f|%f|%f|%f|%f",
                        "LEASE", contract.getDate(),// CONTRACT_TYPE|DATE|
                        contract.getCustomerName(), contract.getCustomerEmail(),// CUSTOMER_NAME|CUSTOMER_EMAIL|
                        vehicleSold.getVin(), vehicleSold.getYear(), // VIN|YEAR|
                        vehicleSold.getMake(), vehicleSold.getModel(), // MAKE|MODEL|
                        vehicleSold.getVehicleType(), vehicleSold.getColor(), // VEHICLE_TYPE|COLOR|
                        vehicleSold.getOdometer(), vehicleSold.getPrice(), // ODOMETER|VEHICLE_PRICE|
                        ((LeaseContract) contract).getExpectedEndingValue(), // EXPECTED_ENDING_VALUE|
                        ((LeaseContract) contract).getLeaseFee(), // LEASE_FEE_PAYMENT
                        contract.getTotalPrice(), contract.getMonthlyPayment() // TOTAL_PRICE|MONTHLY_PAYMENT
                );
                contractsCsv.append(leaseContract);
            }
        }

        return contractsCsv.toString();
    }
}
