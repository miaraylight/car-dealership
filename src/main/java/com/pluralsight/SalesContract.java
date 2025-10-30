package com.pluralsight;

public class SalesContract extends Contract{
    private int salesTaxAmount; // (5%)
    private double recordingFee; // ($100)
    private double processingFee; // ($295 for vehicles under $10,000 and $495 for all others
    private boolean isFinance;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, int salesTaxAmount, double recordingFee, double processingFee, boolean isFinance, double monthlyPayment) {
        super(date, customerName, customerEmail, vehicleSold);
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.isFinance = isFinance;
    }

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, int salesTaxAmount, double recordingFee, double processingFee, boolean isFinance) {
        super(date, customerName, customerEmail, vehicleSold);
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.isFinance = isFinance;
    }

    public int getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(int salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinance() {
        return isFinance;
    }

    public void setFinance(boolean finance) {
        isFinance = finance;
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
