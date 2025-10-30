package com.pluralsight;

public class LeaseContract extends Contract{
    private double expectedEndingValue;
    private int leaseFee;
    private double totalPrice;
    private double monthlyPayment;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, double expectedEndingValue, int leaseFee) {
        super(date, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = expectedEndingValue;
        this.leaseFee = leaseFee;
    }

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold,
                         double expectedEndingValue,
                         int leaseFee,
                         double totalPrice,
                         double monthlyPayment) {
        super(date, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = expectedEndingValue;
        this.leaseFee = leaseFee;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public int getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(int leaseFee) {
        this.leaseFee = leaseFee;
    }

    public double getTotalPrice() {
        return 123;
    };

    public double getMonthlyPayment() {
        //All leases are financed at 4.0% for 36 months
        return 0;
    };
}
