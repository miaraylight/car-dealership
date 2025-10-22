package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DealershipFileManager {

    static public Dealership getDealership() {
        Dealership dealership = new Dealership();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("inventory.csv"))){
            String[] dealershipData = bufferedReader.readLine().split("\\|");


            dealership = new Dealership(dealershipData[0], dealershipData[1], dealershipData[2]);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership) {

    }
}
