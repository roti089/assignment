package com.pims.model;

public class Sale {

    private int saleId;
    private String saleDate;
    private String cashier;
    private double totalAmount;

    public Sale() {
    }

    public Sale(
            int saleId,
            String saleDate,
            String cashier,
            double totalAmount) {

        this.saleId = saleId;
        this.saleDate = saleDate;
        this.cashier = cashier;
        this.totalAmount = totalAmount;
    }

    public String get_sale_date() {
        return saleDate;
    }

    public void set_sale_date(String saleDate) {
        this.saleDate = saleDate;
    }

    public String get_cashier() {
        return cashier;
    }

    public void set_cashier(String cashier) {
        this.cashier = cashier;
    }

    public double get_total_amount() {
        return totalAmount;
    }

    public void set_total_amount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int get_sale_id() {
        return saleId;
    }

    public void set_sale_id(int saleId) {
        this.saleId = saleId;
    }
}