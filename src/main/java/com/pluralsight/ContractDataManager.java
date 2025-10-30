package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {


    public static void saveContract(Contract contract){
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
