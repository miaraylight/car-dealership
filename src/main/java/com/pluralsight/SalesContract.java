package com.pluralsight;

public class SalesContract extends Contract{
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private double totalPrice;
    private boolean isFinance;
    private double monthlyPayment;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinance) {
        super(date, customerName, customerEmail, vehicleSold);
        this.isFinance = isFinance;
    }

    // read from csv
    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold,
            double salesTaxAmount,
            double recordingFee,
            double processingFee,
            double totalPrice,
            boolean isFinance,
            double monthlyPayment) {
        super(date, customerName, customerEmail, vehicleSold);
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.totalPrice = totalPrice;
        this.isFinance = isFinance;
        this.monthlyPayment = monthlyPayment;
    }

    public double getSalesTaxAmount() {
        return getTotalPrice() * 0.05;// (5%)
    }

    public double getRecordingFee() {
        return 100;// ($100)
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public boolean isFinance() {
        return isFinance;
    }

    public void setFinance(boolean finance) {
        isFinance = finance;
    }

    public double getProcessingFee() {
        Vehicle vehicleSold = super.getVehicleSold();
        double priceOfVehicle = vehicleSold.getPrice();
        double processingFee = 295;

        if (priceOfVehicle > 10000) {
            processingFee = 495;
        }

        return processingFee;
    }

    public double getTotalPrice() {
        return 123;
    };

    public double getMonthlyPayment() {

        if (isFinance) {
            // • All loans are at 4.25% for 48 months if the price is $10,000 or more
            // • Otherwise they are at 5.25% for 24 month
        }
        return 0;
    };
}
