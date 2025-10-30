package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class ContractData {
    private ArrayList<Contract> contracts = new ArrayList<>();

    public ContractData(ArrayList<Contract> contracts) {
        this.contracts = contracts;
    }

    public List<Contract> getAllContracts(){
        return contracts;
    }

    public void addContract(Contract contract) {
        contracts.add(contract);
    }
}
