package com.pluralsight;

public class LeaseContract extends Contract{
    private double expectedEndingValue; // (50% of the original price
    private double leaseFee; //(7% of the original price
    private double totalPrice;
    private double monthlyPayment;

    // when new add
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        this.leaseFee = getLeaseFee();

    }

    // from csv
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold,
                         double expectedEndingValue,
                         double leaseFee,
                         double totalPrice,
                         double monthlyPayment) {
        super(date, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = expectedEndingValue;
        this.leaseFee = leaseFee;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
    }

    public double getExpectedEndingValue() {
        return super.getVehicleSold().getPrice() * 0.50;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return super.getVehicleSold().getPrice() * 0.07;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    public double getTotalPrice() {
        return (super.getVehicleSold().getPrice() - getExpectedEndingValue()) + getLeaseFee();
    };

    public double getMonthlyPayment() {
        //All leases are financed at 4.0% for 36 months
        double monthlyInterestRate = 0.04 / 12;
        double totalPrice = getTotalPrice();
        int numberOfMonth = 36;
        double monthlyPayment = (monthlyInterestRate * totalPrice) /
                (1 - Math.pow(1 + monthlyInterestRate, -numberOfMonth));
        return monthlyPayment;
    };

    @Override
    public String toString() {
        return "LeaseContract{" +
                "expectedEndingValue=" + expectedEndingValue +
                ", leaseFee=" + leaseFee +
                ", totalPrice=" + totalPrice +
                ", monthlyPayment=" + monthlyPayment +
                '}';
    }
}
